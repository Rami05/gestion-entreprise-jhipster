package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;

import com.mycompany.myapp.domain.Permis_de_conduit;
import com.mycompany.myapp.repository.Permis_de_conduitRepository;
import com.mycompany.myapp.service.Permis_de_conduitService;
import com.mycompany.myapp.service.dto.Permis_de_conduitDTO;
import com.mycompany.myapp.service.mapper.Permis_de_conduitMapper;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Permis_de_conduitResource REST controller.
 *
 * @see Permis_de_conduitResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class Permis_de_conduitResourceIntTest {

    private static final String DEFAULT_NUM_PERMIS = "AAAAAAAAAA";
    private static final String UPDATED_NUM_PERMIS = "BBBBBBBBBB";

    private static final byte[] DEFAULT_COPIE_PERMIS = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_COPIE_PERMIS = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_COPIE_PERMIS_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_COPIE_PERMIS_CONTENT_TYPE = "image/png";

    @Autowired
    private Permis_de_conduitRepository permis_de_conduitRepository;

    @Autowired
    private Permis_de_conduitMapper permis_de_conduitMapper;

    @Autowired
    private Permis_de_conduitService permis_de_conduitService;

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

    private MockMvc restPermis_de_conduitMockMvc;

    private Permis_de_conduit permis_de_conduit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Permis_de_conduitResource permis_de_conduitResource = new Permis_de_conduitResource(permis_de_conduitService);
        this.restPermis_de_conduitMockMvc = MockMvcBuilders.standaloneSetup(permis_de_conduitResource)
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
    public static Permis_de_conduit createEntity(EntityManager em) {
        Permis_de_conduit permis_de_conduit = new Permis_de_conduit()
            .num_permis(DEFAULT_NUM_PERMIS)
            .copie_permis(DEFAULT_COPIE_PERMIS)
            .copie_permisContentType(DEFAULT_COPIE_PERMIS_CONTENT_TYPE);
        return permis_de_conduit;
    }

    @Before
    public void initTest() {
        permis_de_conduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createPermis_de_conduit() throws Exception {
        int databaseSizeBeforeCreate = permis_de_conduitRepository.findAll().size();

        // Create the Permis_de_conduit
        Permis_de_conduitDTO permis_de_conduitDTO = permis_de_conduitMapper.toDto(permis_de_conduit);
        restPermis_de_conduitMockMvc.perform(post("/api/permis-de-conduits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(permis_de_conduitDTO)))
            .andExpect(status().isCreated());

        // Validate the Permis_de_conduit in the database
        List<Permis_de_conduit> permis_de_conduitList = permis_de_conduitRepository.findAll();
        assertThat(permis_de_conduitList).hasSize(databaseSizeBeforeCreate + 1);
        Permis_de_conduit testPermis_de_conduit = permis_de_conduitList.get(permis_de_conduitList.size() - 1);
        assertThat(testPermis_de_conduit.getNum_permis()).isEqualTo(DEFAULT_NUM_PERMIS);
        assertThat(testPermis_de_conduit.getCopie_permis()).isEqualTo(DEFAULT_COPIE_PERMIS);
        assertThat(testPermis_de_conduit.getCopie_permisContentType()).isEqualTo(DEFAULT_COPIE_PERMIS_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createPermis_de_conduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = permis_de_conduitRepository.findAll().size();

        // Create the Permis_de_conduit with an existing ID
        permis_de_conduit.setId(1L);
        Permis_de_conduitDTO permis_de_conduitDTO = permis_de_conduitMapper.toDto(permis_de_conduit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPermis_de_conduitMockMvc.perform(post("/api/permis-de-conduits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(permis_de_conduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Permis_de_conduit in the database
        List<Permis_de_conduit> permis_de_conduitList = permis_de_conduitRepository.findAll();
        assertThat(permis_de_conduitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNum_permisIsRequired() throws Exception {
        int databaseSizeBeforeTest = permis_de_conduitRepository.findAll().size();
        // set the field null
        permis_de_conduit.setNum_permis(null);

        // Create the Permis_de_conduit, which fails.
        Permis_de_conduitDTO permis_de_conduitDTO = permis_de_conduitMapper.toDto(permis_de_conduit);

        restPermis_de_conduitMockMvc.perform(post("/api/permis-de-conduits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(permis_de_conduitDTO)))
            .andExpect(status().isBadRequest());

        List<Permis_de_conduit> permis_de_conduitList = permis_de_conduitRepository.findAll();
        assertThat(permis_de_conduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPermis_de_conduits() throws Exception {
        // Initialize the database
        permis_de_conduitRepository.saveAndFlush(permis_de_conduit);

        // Get all the permis_de_conduitList
        restPermis_de_conduitMockMvc.perform(get("/api/permis-de-conduits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(permis_de_conduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].num_permis").value(hasItem(DEFAULT_NUM_PERMIS.toString())))
            .andExpect(jsonPath("$.[*].copie_permisContentType").value(hasItem(DEFAULT_COPIE_PERMIS_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].copie_permis").value(hasItem(Base64Utils.encodeToString(DEFAULT_COPIE_PERMIS))));
    }
    
    @Test
    @Transactional
    public void getPermis_de_conduit() throws Exception {
        // Initialize the database
        permis_de_conduitRepository.saveAndFlush(permis_de_conduit);

        // Get the permis_de_conduit
        restPermis_de_conduitMockMvc.perform(get("/api/permis-de-conduits/{id}", permis_de_conduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(permis_de_conduit.getId().intValue()))
            .andExpect(jsonPath("$.num_permis").value(DEFAULT_NUM_PERMIS.toString()))
            .andExpect(jsonPath("$.copie_permisContentType").value(DEFAULT_COPIE_PERMIS_CONTENT_TYPE))
            .andExpect(jsonPath("$.copie_permis").value(Base64Utils.encodeToString(DEFAULT_COPIE_PERMIS)));
    }

    @Test
    @Transactional
    public void getNonExistingPermis_de_conduit() throws Exception {
        // Get the permis_de_conduit
        restPermis_de_conduitMockMvc.perform(get("/api/permis-de-conduits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePermis_de_conduit() throws Exception {
        // Initialize the database
        permis_de_conduitRepository.saveAndFlush(permis_de_conduit);

        int databaseSizeBeforeUpdate = permis_de_conduitRepository.findAll().size();

        // Update the permis_de_conduit
        Permis_de_conduit updatedPermis_de_conduit = permis_de_conduitRepository.findById(permis_de_conduit.getId()).get();
        // Disconnect from session so that the updates on updatedPermis_de_conduit are not directly saved in db
        em.detach(updatedPermis_de_conduit);
        updatedPermis_de_conduit
            .num_permis(UPDATED_NUM_PERMIS)
            .copie_permis(UPDATED_COPIE_PERMIS)
            .copie_permisContentType(UPDATED_COPIE_PERMIS_CONTENT_TYPE);
        Permis_de_conduitDTO permis_de_conduitDTO = permis_de_conduitMapper.toDto(updatedPermis_de_conduit);

        restPermis_de_conduitMockMvc.perform(put("/api/permis-de-conduits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(permis_de_conduitDTO)))
            .andExpect(status().isOk());

        // Validate the Permis_de_conduit in the database
        List<Permis_de_conduit> permis_de_conduitList = permis_de_conduitRepository.findAll();
        assertThat(permis_de_conduitList).hasSize(databaseSizeBeforeUpdate);
        Permis_de_conduit testPermis_de_conduit = permis_de_conduitList.get(permis_de_conduitList.size() - 1);
        assertThat(testPermis_de_conduit.getNum_permis()).isEqualTo(UPDATED_NUM_PERMIS);
        assertThat(testPermis_de_conduit.getCopie_permis()).isEqualTo(UPDATED_COPIE_PERMIS);
        assertThat(testPermis_de_conduit.getCopie_permisContentType()).isEqualTo(UPDATED_COPIE_PERMIS_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingPermis_de_conduit() throws Exception {
        int databaseSizeBeforeUpdate = permis_de_conduitRepository.findAll().size();

        // Create the Permis_de_conduit
        Permis_de_conduitDTO permis_de_conduitDTO = permis_de_conduitMapper.toDto(permis_de_conduit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPermis_de_conduitMockMvc.perform(put("/api/permis-de-conduits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(permis_de_conduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Permis_de_conduit in the database
        List<Permis_de_conduit> permis_de_conduitList = permis_de_conduitRepository.findAll();
        assertThat(permis_de_conduitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePermis_de_conduit() throws Exception {
        // Initialize the database
        permis_de_conduitRepository.saveAndFlush(permis_de_conduit);

        int databaseSizeBeforeDelete = permis_de_conduitRepository.findAll().size();

        // Delete the permis_de_conduit
        restPermis_de_conduitMockMvc.perform(delete("/api/permis-de-conduits/{id}", permis_de_conduit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Permis_de_conduit> permis_de_conduitList = permis_de_conduitRepository.findAll();
        assertThat(permis_de_conduitList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Permis_de_conduit.class);
        Permis_de_conduit permis_de_conduit1 = new Permis_de_conduit();
        permis_de_conduit1.setId(1L);
        Permis_de_conduit permis_de_conduit2 = new Permis_de_conduit();
        permis_de_conduit2.setId(permis_de_conduit1.getId());
        assertThat(permis_de_conduit1).isEqualTo(permis_de_conduit2);
        permis_de_conduit2.setId(2L);
        assertThat(permis_de_conduit1).isNotEqualTo(permis_de_conduit2);
        permis_de_conduit1.setId(null);
        assertThat(permis_de_conduit1).isNotEqualTo(permis_de_conduit2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Permis_de_conduitDTO.class);
        Permis_de_conduitDTO permis_de_conduitDTO1 = new Permis_de_conduitDTO();
        permis_de_conduitDTO1.setId(1L);
        Permis_de_conduitDTO permis_de_conduitDTO2 = new Permis_de_conduitDTO();
        assertThat(permis_de_conduitDTO1).isNotEqualTo(permis_de_conduitDTO2);
        permis_de_conduitDTO2.setId(permis_de_conduitDTO1.getId());
        assertThat(permis_de_conduitDTO1).isEqualTo(permis_de_conduitDTO2);
        permis_de_conduitDTO2.setId(2L);
        assertThat(permis_de_conduitDTO1).isNotEqualTo(permis_de_conduitDTO2);
        permis_de_conduitDTO1.setId(null);
        assertThat(permis_de_conduitDTO1).isNotEqualTo(permis_de_conduitDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(permis_de_conduitMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(permis_de_conduitMapper.fromId(null)).isNull();
    }
}
