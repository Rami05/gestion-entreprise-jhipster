package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.Compte_bancaireService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.Compte_bancaireDTO;
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
 * REST controller for managing Compte_bancaire.
 */
@RestController
@RequestMapping("/api")
public class Compte_bancaireResource {

    private final Logger log = LoggerFactory.getLogger(Compte_bancaireResource.class);

    private static final String ENTITY_NAME = "compte_bancaire";

    private final Compte_bancaireService compte_bancaireService;

    public Compte_bancaireResource(Compte_bancaireService compte_bancaireService) {
        this.compte_bancaireService = compte_bancaireService;
    }

    /**
     * POST  /compte-bancaires : Create a new compte_bancaire.
     *
     * @param compte_bancaireDTO the compte_bancaireDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new compte_bancaireDTO, or with status 400 (Bad Request) if the compte_bancaire has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/compte-bancaires")
    public ResponseEntity<Compte_bancaireDTO> createCompte_bancaire(@Valid @RequestBody Compte_bancaireDTO compte_bancaireDTO) throws URISyntaxException {
        log.debug("REST request to save Compte_bancaire : {}", compte_bancaireDTO);
        if (compte_bancaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new compte_bancaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Compte_bancaireDTO result = compte_bancaireService.save(compte_bancaireDTO);
        return ResponseEntity.created(new URI("/api/compte-bancaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /compte-bancaires : Updates an existing compte_bancaire.
     *
     * @param compte_bancaireDTO the compte_bancaireDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated compte_bancaireDTO,
     * or with status 400 (Bad Request) if the compte_bancaireDTO is not valid,
     * or with status 500 (Internal Server Error) if the compte_bancaireDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/compte-bancaires")
    public ResponseEntity<Compte_bancaireDTO> updateCompte_bancaire(@Valid @RequestBody Compte_bancaireDTO compte_bancaireDTO) throws URISyntaxException {
        log.debug("REST request to update Compte_bancaire : {}", compte_bancaireDTO);
        if (compte_bancaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Compte_bancaireDTO result = compte_bancaireService.save(compte_bancaireDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, compte_bancaireDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /compte-bancaires : get all the compte_bancaires.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of compte_bancaires in body
     */
    @GetMapping("/compte-bancaires")
    public List<Compte_bancaireDTO> getAllCompte_bancaires() {
        log.debug("REST request to get all Compte_bancaires");
        return compte_bancaireService.findAll();
    }

    /**
     * GET  /compte-bancaires/:id : get the "id" compte_bancaire.
     *
     * @param id the id of the compte_bancaireDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the compte_bancaireDTO, or with status 404 (Not Found)
     */
    @GetMapping("/compte-bancaires/{id}")
    public ResponseEntity<Compte_bancaireDTO> getCompte_bancaire(@PathVariable Long id) {
        log.debug("REST request to get Compte_bancaire : {}", id);
        Optional<Compte_bancaireDTO> compte_bancaireDTO = compte_bancaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(compte_bancaireDTO);
    }

    /**
     * DELETE  /compte-bancaires/:id : delete the "id" compte_bancaire.
     *
     * @param id the id of the compte_bancaireDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/compte-bancaires/{id}")
    public ResponseEntity<Void> deleteCompte_bancaire(@PathVariable Long id) {
        log.debug("REST request to delete Compte_bancaire : {}", id);
        compte_bancaireService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
