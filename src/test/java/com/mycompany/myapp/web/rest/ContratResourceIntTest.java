package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;

import com.mycompany.myapp.domain.Contrat;
import com.mycompany.myapp.repository.ContratRepository;
import com.mycompany.myapp.service.ContratService;
import com.mycompany.myapp.service.dto.ContratDTO;
import com.mycompany.myapp.service.mapper.ContratMapper;
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
 * Test class for the ContratResource REST controller.
 *
 * @see ContratResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class ContratResourceIntTest {

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    @Autowired
    private ContratRepository contratRepository;

    @Autowired
    private ContratMapper contratMapper;

    @Autowired
    private ContratService contratService;

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

    private MockMvc restContratMockMvc;

    private Contrat contrat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContratResource contratResource = new ContratResource(contratService);
        this.restContratMockMvc = MockMvcBuilders.standaloneSetup(contratResource)
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
    public static Contrat createEntity(EntityManager em) {
        Contrat contrat = new Contrat()
            .reference(DEFAULT_REFERENCE);
        return contrat;
    }

    @Before
    public void initTest() {
        contrat = createEntity(em);
    }

    @Test
    @Transactional
    public void createContrat() throws Exception {
        int databaseSizeBeforeCreate = contratRepository.findAll().size();

        // Create the Contrat
        ContratDTO contratDTO = contratMapper.toDto(contrat);
        restContratMockMvc.perform(post("/api/contrats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratDTO)))
            .andExpect(status().isCreated());

        // Validate the Contrat in the database
        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeCreate + 1);
        Contrat testContrat = contratList.get(contratList.size() - 1);
        assertThat(testContrat.getReference()).isEqualTo(DEFAULT_REFERENCE);
    }

    @Test
    @Transactional
    public void createContratWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contratRepository.findAll().size();

        // Create the Contrat with an existing ID
        contrat.setId(1L);
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContratMockMvc.perform(post("/api/contrats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contrat in the database
        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllContrats() throws Exception {
        // Initialize the database
        contratRepository.saveAndFlush(contrat);

        // Get all the contratList
        restContratMockMvc.perform(get("/api/contrats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contrat.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())));
    }
    
    @Test
    @Transactional
    public void getContrat() throws Exception {
        // Initialize the database
        contratRepository.saveAndFlush(contrat);

        // Get the contrat
        restContratMockMvc.perform(get("/api/contrats/{id}", contrat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contrat.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContrat() throws Exception {
        // Get the contrat
        restContratMockMvc.perform(get("/api/contrats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContrat() throws Exception {
        // Initialize the database
        contratRepository.saveAndFlush(contrat);

        int databaseSizeBeforeUpdate = contratRepository.findAll().size();

        // Update the contrat
        Contrat updatedContrat = contratRepository.findById(contrat.getId()).get();
        // Disconnect from session so that the updates on updatedContrat are not directly saved in db
        em.detach(updatedContrat);
        updatedContrat
            .reference(UPDATED_REFERENCE);
        ContratDTO contratDTO = contratMapper.toDto(updatedContrat);

        restContratMockMvc.perform(put("/api/contrats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratDTO)))
            .andExpect(status().isOk());

        // Validate the Contrat in the database
        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeUpdate);
        Contrat testContrat = contratList.get(contratList.size() - 1);
        assertThat(testContrat.getReference()).isEqualTo(UPDATED_REFERENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingContrat() throws Exception {
        int databaseSizeBeforeUpdate = contratRepository.findAll().size();

        // Create the Contrat
        ContratDTO contratDTO = contratMapper.toDto(contrat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContratMockMvc.perform(put("/api/contrats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contratDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Contrat in the database
        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContrat() throws Exception {
        // Initialize the database
        contratRepository.saveAndFlush(contrat);

        int databaseSizeBeforeDelete = contratRepository.findAll().size();

        // Delete the contrat
        restContratMockMvc.perform(delete("/api/contrats/{id}", contrat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Contrat> contratList = contratRepository.findAll();
        assertThat(contratList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contrat.class);
        Contrat contrat1 = new Contrat();
        contrat1.setId(1L);
        Contrat contrat2 = new Contrat();
        contrat2.setId(contrat1.getId());
        assertThat(contrat1).isEqualTo(contrat2);
        contrat2.setId(2L);
        assertThat(contrat1).isNotEqualTo(contrat2);
        contrat1.setId(null);
        assertThat(contrat1).isNotEqualTo(contrat2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContratDTO.class);
        ContratDTO contratDTO1 = new ContratDTO();
        contratDTO1.setId(1L);
        ContratDTO contratDTO2 = new ContratDTO();
        assertThat(contratDTO1).isNotEqualTo(contratDTO2);
        contratDTO2.setId(contratDTO1.getId());
        assertThat(contratDTO1).isEqualTo(contratDTO2);
        contratDTO2.setId(2L);
        assertThat(contratDTO1).isNotEqualTo(contratDTO2);
        contratDTO1.setId(null);
        assertThat(contratDTO1).isNotEqualTo(contratDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contratMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contratMapper.fromId(null)).isNull();
    }
}
