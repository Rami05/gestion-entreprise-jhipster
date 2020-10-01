package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.ChauffeurService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.ChauffeurDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Chauffeur.
 */
@RestController
@RequestMapping("/api")
public class ChauffeurResource {

    private final Logger log = LoggerFactory.getLogger(ChauffeurResource.class);

    private static final String ENTITY_NAME = "chauffeur";

    private final ChauffeurService chauffeurService;

    public ChauffeurResource(ChauffeurService chauffeurService) {
        this.chauffeurService = chauffeurService;
    }

    /**
     * POST  /chauffeurs : Create a new chauffeur.
     *
     * @param chauffeurDTO the chauffeurDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chauffeurDTO, or with status 400 (Bad Request) if the chauffeur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/chauffeurs")
    public ResponseEntity<ChauffeurDTO> createChauffeur(@Valid @RequestBody ChauffeurDTO chauffeurDTO) throws URISyntaxException {
        log.debug("REST request to save Chauffeur : {}", chauffeurDTO);
        if (chauffeurDTO.getId() != null) {
            throw new BadRequestAlertException("A new chauffeur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChauffeurDTO result = chauffeurService.save(chauffeurDTO);
        return ResponseEntity.created(new URI("/api/chauffeurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chauffeurs : Updates an existing chauffeur.
     *
     * @param chauffeurDTO the chauffeurDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chauffeurDTO,
     * or with status 400 (Bad Request) if the chauffeurDTO is not valid,
     * or with status 500 (Internal Server Error) if the chauffeurDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/chauffeurs")
    public ResponseEntity<ChauffeurDTO> updateChauffeur(@Valid @RequestBody ChauffeurDTO chauffeurDTO) throws URISyntaxException {
        log.debug("REST request to update Chauffeur : {}", chauffeurDTO);
        if (chauffeurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChauffeurDTO result = chauffeurService.save(chauffeurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chauffeurDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chauffeurs : get all the chauffeurs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of chauffeurs in body
     */
    @GetMapping("/chauffeurs")
    public List<ChauffeurDTO> getAllChauffeurs() {
        log.debug("REST request to get all Chauffeurs");
        return chauffeurService.findAll();
    }

    /**
     * GET  /chauffeurs/:id : get the "id" chauffeur.
     *
     * @param id the id of the chauffeurDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chauffeurDTO, or with status 404 (Not Found)
     */
    @GetMapping("/chauffeurs/{id}")
    public ResponseEntity<ChauffeurDTO> getChauffeur(@PathVariable Long id) {
        log.debug("REST request to get Chauffeur : {}", id);
        Optional<ChauffeurDTO> chauffeurDTO = chauffeurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chauffeurDTO);
    }

    /**
     * DELETE  /chauffeurs/:id : delete the "id" chauffeur.
     *
     * @param id the id of the chauffeurDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/chauffeurs/{id}")
    public ResponseEntity<Void> deleteChauffeur(@PathVariable Long id) {
        log.debug("REST request to delete Chauffeur : {}", id);
        chauffeurService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
