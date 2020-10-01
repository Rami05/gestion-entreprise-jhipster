package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;

import com.mycompany.myapp.domain.Chauffeur;
import com.mycompany.myapp.repository.ChauffeurRepository;
import com.mycompany.myapp.service.ChauffeurService;
import com.mycompany.myapp.service.dto.ChauffeurDTO;
import com.mycompany.myapp.service.mapper.ChauffeurMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ChauffeurResource REST controller.
 *
 * @see ChauffeurResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class ChauffeurResourceIntTest {

    private static final String DEFAULT_IDENTITE = "AAAAAAAAAA";
    private static final String UPDATED_IDENTITE = "BBBBBBBBBB";

    private static final String DEFAULT_CIN = "AAAAAAAA";
    private static final String UPDATED_CIN = "BBBBBBBB";

    @Autowired
    private ChauffeurRepository chauffeurRepository;

    @Autowired
    private ChauffeurMapper chauffeurMapper;

    @Autowired
    private ChauffeurService chauffeurService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restChauffeurMockMvc;

    private Chauffeur chauffeur;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChauffeurResource chauffeurResource = new ChauffeurResource(chauffeurService);
        this.restChauffeurMockMvc = MockMvcBuilders.standaloneSetup(chauffeurResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Chauffeur createEntity(EntityManager em) {
        Chauffeur chauffeur = new Chauffeur()
            .identite(DEFAULT_IDENTITE)
            .cin(DEFAULT_CIN);
        return chauffeur;
    }

    @Before
    public void initTest() {
        chauffeur = createEntity(em);
    }

    @Test
    @Transactional
    public void createChauffeur() throws Exception {
        int databaseSizeBeforeCreate = chauffeurRepository.findAll().size();

        // Create the Chauffeur
        ChauffeurDTO chauffeurDTO = chauffeurMapper.toDto(chauffeur);
        restChauffeurMockMvc.perform(post("/api/chauffeurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chauffeurDTO)))
            .andExpect(status().isCreated());

        // Validate the Chauffeur in the database
        List<Chauffeur> chauffeurList = chauffeurRepository.findAll();
        assertThat(chauffeurList).hasSize(databaseSizeBeforeCreate + 1);
        Chauffeur testChauffeur = chauffeurList.get(chauffeurList.size() - 1);
        assertThat(testChauffeur.getIdentite()).isEqualTo(DEFAULT_IDENTITE);
        assertThat(testChauffeur.getCin()).isEqualTo(DEFAULT_CIN);
    }

    @Test
    @Transactional
    public void createChauffeurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chauffeurRepository.findAll().size();

        // Create the Chauffeur with an existing ID
        chauffeur.setId(1L);
        ChauffeurDTO chauffeurDTO = chauffeurMapper.toDto(chauffeur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChauffeurMockMvc.perform(post("/api/chauffeurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chauffeurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chauffeur in the database
        List<Chauffeur> chauffeurList = chauffeurRepository.findAll();
        assertThat(chauffeurList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCinIsRequired() throws Exception {
        int databaseSizeBeforeTest = chauffeurRepository.findAll().size();
        // set the field null
        chauffeur.setCin(null);

        // Create the Chauffeur, which fails.
        ChauffeurDTO chauffeurDTO = chauffeurMapper.toDto(chauffeur);

        restChauffeurMockMvc.perform(post("/api/chauffeurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chauffeurDTO)))
            .andExpect(status().isBadRequest());

        List<Chauffeur> chauffeurList = chauffeurRepository.findAll();
        assertThat(chauffeurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChauffeurs() throws Exception {
        // Initialize the database
        chauffeurRepository.saveAndFlush(chauffeur);

        // Get all the chauffeurList
        restChauffeurMockMvc.perform(get("/api/chauffeurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chauffeur.getId().intValue())))
            .andExpect(jsonPath("$.[*].identite").value(hasItem(DEFAULT_IDENTITE.toString())))
            .andExpect(jsonPath("$.[*].cin").value(hasItem(DEFAULT_CIN.toString())));
    }
    
    @Test
    @Transactional
    public void getChauffeur() throws Exception {
        // Initialize the database
        chauffeurRepository.saveAndFlush(chauffeur);

        // Get the chauffeur
        restChauffeurMockMvc.perform(get("/api/chauffeurs/{id}", chauffeur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chauffeur.getId().intValue()))
            .andExpect(jsonPath("$.identite").value(DEFAULT_IDENTITE.toString()))
            .andExpect(jsonPath("$.cin").value(DEFAULT_CIN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingChauffeur() throws Exception {
        // Get the chauffeur
        restChauffeurMockMvc.perform(get("/api/chauffeurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChauffeur() throws Exception {
        // Initialize the database
        chauffeurRepository.saveAndFlush(chauffeur);

        int databaseSizeBeforeUpdate = chauffeurRepository.findAll().size();

        // Update the chauffeur
        Chauffeur updatedChauffeur = chauffeurRepository.findById(chauffeur.getId()).get();
        // Disconnect from session so that the updates on updatedChauffeur are not directly saved in db
        em.detach(updatedChauffeur);
        updatedChauffeur
            .identite(UPDATED_IDENTITE)
            .cin(UPDATED_CIN);
        ChauffeurDTO chauffeurDTO = chauffeurMapper.toDto(updatedChauffeur);

        restChauffeurMockMvc.perform(put("/api/chauffeurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chauffeurDTO)))
            .andExpect(status().isOk());

        // Validate the Chauffeur in the database
        List<Chauffeur> chauffeurList = chauffeurRepository.findAll();
        assertThat(chauffeurList).hasSize(databaseSizeBeforeUpdate);
        Chauffeur testChauffeur = chauffeurList.get(chauffeurList.size() - 1);
        assertThat(testChauffeur.getIdentite()).isEqualTo(UPDATED_IDENTITE);
        assertThat(testChauffeur.getCin()).isEqualTo(UPDATED_CIN);
    }

    @Test
    @Transactional
    public void updateNonExistingChauffeur() throws Exception {
        int databaseSizeBeforeUpdate = chauffeurRepository.findAll().size();

        // Create the Chauffeur
        ChauffeurDTO chauffeurDTO = chauffeurMapper.toDto(chauffeur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChauffeurMockMvc.perform(put("/api/chauffeurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chauffeurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chauffeur in the database
        List<Chauffeur> chauffeurList = chauffeurRepository.findAll();
        assertThat(chauffeurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChauffeur() throws Exception {
        // Initialize the database
        chauffeurRepository.saveAndFlush(chauffeur);

        int databaseSizeBeforeDelete = chauffeurRepository.findAll().size();

        // Delete the chauffeur
        restChauffeurMockMvc.perform(delete("/api/chauffeurs/{id}", chauffeur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Chauffeur> chauffeurList = chauffeurRepository.findAll();
        assertThat(chauffeurList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Chauffeur.class);
        Chauffeur chauffeur1 = new Chauffeur();
        chauffeur1.setId(1L);
        Chauffeur chauffeur2 = new Chauffeur();
        chauffeur2.setId(chauffeur1.getId());
        assertThat(chauffeur1).isEqualTo(chauffeur2);
        chauffeur2.setId(2L);
        assertThat(chauffeur1).isNotEqualTo(chauffeur2);
        chauffeur1.setId(null);
        assertThat(chauffeur1).isNotEqualTo(chauffeur2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChauffeurDTO.class);
        ChauffeurDTO chauffeurDTO1 = new ChauffeurDTO();
        chauffeurDTO1.setId(1L);
        ChauffeurDTO chauffeurDTO2 = new ChauffeurDTO();
        assertThat(chauffeurDTO1).isNotEqualTo(chauffeurDTO2);
        chauffeurDTO2.setId(chauffeurDTO1.getId());
        assertThat(chauffeurDTO1).isEqualTo(chauffeurDTO2);
        chauffeurDTO2.setId(2L);
        assertThat(chauffeurDTO1).isNotEqualTo(chauffeurDTO2);
        chauffeurDTO1.setId(null);
        assertThat(chauffeurDTO1).isNotEqualTo(chauffeurDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(chauffeurMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(chauffeurMapper.fromId(null)).isNull();
    }
}
