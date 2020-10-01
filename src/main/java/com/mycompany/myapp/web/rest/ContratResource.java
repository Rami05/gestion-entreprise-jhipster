package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.ContratService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.ContratDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Contrat.
 */
@RestController
@RequestMapping("/api")
public class ContratResource {

    private final Logger log = LoggerFactory.getLogger(ContratResource.class);

    private static final String ENTITY_NAME = "contrat";

    private final ContratService contratService;

    public ContratResource(ContratService contratService) {
        this.contratService = contratService;
    }

    /**
     * POST  /contrats : Create a new contrat.
     *
     * @param contratDTO the contratDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contratDTO, or with status 400 (Bad Request) if the contrat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contrats")
    public ResponseEntity<ContratDTO> createContrat(@RequestBody ContratDTO contratDTO) throws URISyntaxException {
        log.debug("REST request to save Contrat : {}", contratDTO);
        if (contratDTO.getId() != null) {
            throw new BadRequestAlertException("A new contrat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContratDTO result = contratService.save(contratDTO);
        return ResponseEntity.created(new URI("/api/contrats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contrats : Updates an existing contrat.
     *
     * @param contratDTO the contratDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contratDTO,
     * or with status 400 (Bad Request) if the contratDTO is not valid,
     * or with status 500 (Internal Server Error) if the contratDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contrats")
    public ResponseEntity<ContratDTO> updateContrat(@RequestBody ContratDTO contratDTO) throws URISyntaxException {
        log.debug("REST request to update Contrat : {}", contratDTO);
        if (contratDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContratDTO result = contratService.save(contratDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contratDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contrats : get all the contrats.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contrats in body
     */
    @GetMapping("/contrats")
    public List<ContratDTO> getAllContrats() {
        log.debug("REST request to get all Contrats");
        return contratService.findAll();
    }

    /**
     * GET  /contrats/:id : get the "id" contrat.
     *
     * @param id the id of the contratDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contratDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contrats/{id}")
    public ResponseEntity<ContratDTO> getContrat(@PathVariable Long id) {
        log.debug("REST request to get Contrat : {}", id);
        Optional<ContratDTO> contratDTO = contratService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contratDTO);
    }

    /**
     * DELETE  /contrats/:id : delete the "id" contrat.
     *
     * @param id the id of the contratDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contrats/{id}")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        log.debug("REST request to delete Contrat : {}", id);
        contratService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
