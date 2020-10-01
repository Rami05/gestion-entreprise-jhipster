package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;

import com.mycompany.myapp.domain.Compte_bancaire;
import com.mycompany.myapp.repository.Compte_bancaireRepository;
import com.mycompany.myapp.service.Compte_bancaireService;
import com.mycompany.myapp.service.dto.Compte_bancaireDTO;
import com.mycompany.myapp.service.mapper.Compte_bancaireMapper;
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
import java.math.BigDecimal;
import java.util.List;


import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Compte_bancaireResource REST controller.
 *
 * @see Compte_bancaireResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class Compte_bancaireResourceIntTest {

    private static final BigDecimal DEFAULT_RIB = new BigDecimal(1000000000);
    private static final BigDecimal UPDATED_RIB = new BigDecimal(1000000001);

    private static final String DEFAULT_AGENCE = "AAAAAAAAAA";
    private static final String UPDATED_AGENCE = "BBBBBBBBBB";

    @Autowired
    private Compte_bancaireRepository compte_bancaireRepository;

    @Autowired
    private Compte_bancaireMapper compte_bancaireMapper;

    @Autowired
    private Compte_bancaireService compte_bancaireService;

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

    private MockMvc restCompte_bancaireMockMvc;

    private Compte_bancaire compte_bancaire;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Compte_bancaireResource compte_bancaireResource = new Compte_bancaireResource(compte_bancaireService);
        this.restCompte_bancaireMockMvc = MockMvcBuilders.standaloneSetup(compte_bancaireResource)
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
    public static Compte_bancaire createEntity(EntityManager em) {
        Compte_bancaire compte_bancaire = new Compte_bancaire()
            .rib(DEFAULT_RIB)
            .agence(DEFAULT_AGENCE);
        return compte_bancaire;
    }

    @Before
    public void initTest() {
        compte_bancaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompte_bancaire() throws Exception {
        int databaseSizeBeforeCreate = compte_bancaireRepository.findAll().size();

        // Create the Compte_bancaire
        Compte_bancaireDTO compte_bancaireDTO = compte_bancaireMapper.toDto(compte_bancaire);
        restCompte_bancaireMockMvc.perform(post("/api/compte-bancaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compte_bancaireDTO)))
            .andExpect(status().isCreated());

        // Validate the Compte_bancaire in the database
        List<Compte_bancaire> compte_bancaireList = compte_bancaireRepository.findAll();
        assertThat(compte_bancaireList).hasSize(databaseSizeBeforeCreate + 1);
        Compte_bancaire testCompte_bancaire = compte_bancaireList.get(compte_bancaireList.size() - 1);
        assertThat(testCompte_bancaire.getRib()).isEqualTo(DEFAULT_RIB);
        assertThat(testCompte_bancaire.getAgence()).isEqualTo(DEFAULT_AGENCE);
    }

    @Test
    @Transactional
    public void createCompte_bancaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = compte_bancaireRepository.findAll().size();

        // Create the Compte_bancaire with an existing ID
        compte_bancaire.setId(1L);
        Compte_bancaireDTO compte_bancaireDTO = compte_bancaireMapper.toDto(compte_bancaire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompte_bancaireMockMvc.perform(post("/api/compte-bancaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compte_bancaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Compte_bancaire in the database
        List<Compte_bancaire> compte_bancaireList = compte_bancaireRepository.findAll();
        assertThat(compte_bancaireList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCompte_bancaires() throws Exception {
        // Initialize the database
        compte_bancaireRepository.saveAndFlush(compte_bancaire);

        // Get all the compte_bancaireList
        restCompte_bancaireMockMvc.perform(get("/api/compte-bancaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(compte_bancaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].rib").value(hasItem(DEFAULT_RIB.intValue())))
            .andExpect(jsonPath("$.[*].agence").value(hasItem(DEFAULT_AGENCE.toString())));
    }
    
    @Test
    @Transactional
    public void getCompte_bancaire() throws Exception {
        // Initialize the database
        compte_bancaireRepository.saveAndFlush(compte_bancaire);

        // Get the compte_bancaire
        restCompte_bancaireMockMvc.perform(get("/api/compte-bancaires/{id}", compte_bancaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(compte_bancaire.getId().intValue()))
            .andExpect(jsonPath("$.rib").value(DEFAULT_RIB.intValue()))
            .andExpect(jsonPath("$.agence").value(DEFAULT_AGENCE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCompte_bancaire() throws Exception {
        // Get the compte_bancaire
        restCompte_bancaireMockMvc.perform(get("/api/compte-bancaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompte_bancaire() throws Exception {
        // Initialize the database
        compte_bancaireRepository.saveAndFlush(compte_bancaire);

        int databaseSizeBeforeUpdate = compte_bancaireRepository.findAll().size();

        // Update the compte_bancaire
        Compte_bancaire updatedCompte_bancaire = compte_bancaireRepository.findById(compte_bancaire.getId()).get();
        // Disconnect from session so that the updates on updatedCompte_bancaire are not directly saved in db
        em.detach(updatedCompte_bancaire);
        updatedCompte_bancaire
            .rib(UPDATED_RIB)
            .agence(UPDATED_AGENCE);
        Compte_bancaireDTO compte_bancaireDTO = compte_bancaireMapper.toDto(updatedCompte_bancaire);

        restCompte_bancaireMockMvc.perform(put("/api/compte-bancaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compte_bancaireDTO)))
            .andExpect(status().isOk());

        // Validate the Compte_bancaire in the database
        List<Compte_bancaire> compte_bancaireList = compte_bancaireRepository.findAll();
        assertThat(compte_bancaireList).hasSize(databaseSizeBeforeUpdate);
        Compte_bancaire testCompte_bancaire = compte_bancaireList.get(compte_bancaireList.size() - 1);
        assertThat(testCompte_bancaire.getRib()).isEqualTo(UPDATED_RIB);
        assertThat(testCompte_bancaire.getAgence()).isEqualTo(UPDATED_AGENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingCompte_bancaire() throws Exception {
        int databaseSizeBeforeUpdate = compte_bancaireRepository.findAll().size();

        // Create the Compte_bancaire
        Compte_bancaireDTO compte_bancaireDTO = compte_bancaireMapper.toDto(compte_bancaire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompte_bancaireMockMvc.perform(put("/api/compte-bancaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compte_bancaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Compte_bancaire in the database
        List<Compte_bancaire> compte_bancaireList = compte_bancaireRepository.findAll();
        assertThat(compte_bancaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompte_bancaire() throws Exception {
        // Initialize the database
        compte_bancaireRepository.saveAndFlush(compte_bancaire);

        int databaseSizeBeforeDelete = compte_bancaireRepository.findAll().size();

        // Delete the compte_bancaire
        restCompte_bancaireMockMvc.perform(delete("/api/compte-bancaires/{id}", compte_bancaire.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Compte_bancaire> compte_bancaireList = compte_bancaireRepository.findAll();
        assertThat(compte_bancaireList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Compte_bancaire.class);
        Compte_bancaire compte_bancaire1 = new Compte_bancaire();
        compte_bancaire1.setId(1L);
        Compte_bancaire compte_bancaire2 = new Compte_bancaire();
        compte_bancaire2.setId(compte_bancaire1.getId());
        assertThat(compte_bancaire1).isEqualTo(compte_bancaire2);
        compte_bancaire2.setId(2L);
        assertThat(compte_bancaire1).isNotEqualTo(compte_bancaire2);
        compte_bancaire1.setId(null);
        assertThat(compte_bancaire1).isNotEqualTo(compte_bancaire2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Compte_bancaireDTO.class);
        Compte_bancaireDTO compte_bancaireDTO1 = new Compte_bancaireDTO();
        compte_bancaireDTO1.setId(1L);
        Compte_bancaireDTO compte_bancaireDTO2 = new Compte_bancaireDTO();
        assertThat(compte_bancaireDTO1).isNotEqualTo(compte_bancaireDTO2);
        compte_bancaireDTO2.setId(compte_bancaireDTO1.getId());
        assertThat(compte_bancaireDTO1).isEqualTo(compte_bancaireDTO2);
        compte_bancaireDTO2.setId(2L);
        assertThat(compte_bancaireDTO1).isNotEqualTo(compte_bancaireDTO2);
        compte_bancaireDTO1.setId(null);
        assertThat(compte_bancaireDTO1).isNotEqualTo(compte_bancaireDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(compte_bancaireMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(compte_bancaireMapper.fromId(null)).isNull();
    }
}
