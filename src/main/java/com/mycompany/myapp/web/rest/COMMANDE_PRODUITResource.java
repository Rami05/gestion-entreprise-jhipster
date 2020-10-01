package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.COMMANDE_PRODUITService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.COMMANDE_PRODUITDTO;
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
 * REST controller for managing COMMANDE_PRODUIT.
 */
@RestController
@RequestMapping("/api")
public class COMMANDE_PRODUITResource {

    private final Logger log = LoggerFactory.getLogger(COMMANDE_PRODUITResource.class);

    private static final String ENTITY_NAME = "cOMMANDE_PRODUIT";

    private final COMMANDE_PRODUITService cOMMANDE_PRODUITService;

    public COMMANDE_PRODUITResource(COMMANDE_PRODUITService cOMMANDE_PRODUITService) {
        this.cOMMANDE_PRODUITService = cOMMANDE_PRODUITService;
    }

    /**
     * POST  /commande-produits : Create a new cOMMANDE_PRODUIT.
     *
     * @param cOMMANDE_PRODUITDTO the cOMMANDE_PRODUITDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cOMMANDE_PRODUITDTO, or with status 400 (Bad Request) if the cOMMANDE_PRODUIT has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/commande-produits")
    public ResponseEntity<COMMANDE_PRODUITDTO> createCOMMANDE_PRODUIT(@RequestBody COMMANDE_PRODUITDTO cOMMANDE_PRODUITDTO) throws URISyntaxException {
        log.debug("REST request to save COMMANDE_PRODUIT : {}", cOMMANDE_PRODUITDTO);
        if (cOMMANDE_PRODUITDTO.getId() != null) {
            throw new BadRequestAlertException("A new cOMMANDE_PRODUIT cannot already have an ID", ENTITY_NAME, "idexists");
        }
        COMMANDE_PRODUITDTO result = cOMMANDE_PRODUITService.save(cOMMANDE_PRODUITDTO);
        return ResponseEntity.created(new URI("/api/commande-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /commande-produits : Updates an existing cOMMANDE_PRODUIT.
     *
     * @param cOMMANDE_PRODUITDTO the cOMMANDE_PRODUITDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cOMMANDE_PRODUITDTO,
     * or with status 400 (Bad Request) if the cOMMANDE_PRODUITDTO is not valid,
     * or with status 500 (Internal Server Error) if the cOMMANDE_PRODUITDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/commande-produits")
    public ResponseEntity<COMMANDE_PRODUITDTO> updateCOMMANDE_PRODUIT(@RequestBody COMMANDE_PRODUITDTO cOMMANDE_PRODUITDTO) throws URISyntaxException {
        log.debug("REST request to update COMMANDE_PRODUIT : {}", cOMMANDE_PRODUITDTO);
        if (cOMMANDE_PRODUITDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        COMMANDE_PRODUITDTO result = cOMMANDE_PRODUITService.save(cOMMANDE_PRODUITDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cOMMANDE_PRODUITDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /commande-produits : get all the cOMMANDE_PRODUITS.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cOMMANDE_PRODUITS in body
     */
    @GetMapping("/commande-produits")
    public List<COMMANDE_PRODUITDTO> getAllCOMMANDE_PRODUITS() {
        log.debug("REST request to get all COMMANDE_PRODUITS");
        return cOMMANDE_PRODUITService.findAll();
    }

    /**
     * GET  /commande-produits/:id : get the "id" cOMMANDE_PRODUIT.
     *
     * @param id the id of the cOMMANDE_PRODUITDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cOMMANDE_PRODUITDTO, or with status 404 (Not Found)
     */
    @GetMapping("/commande-produits/{id}")
    public ResponseEntity<COMMANDE_PRODUITDTO> getCOMMANDE_PRODUIT(@PathVariable Long id) {
        log.debug("REST request to get COMMANDE_PRODUIT : {}", id);
        Optional<COMMANDE_PRODUITDTO> cOMMANDE_PRODUITDTO = cOMMANDE_PRODUITService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cOMMANDE_PRODUITDTO);
    }

    /**
     * DELETE  /commande-produits/:id : delete the "id" cOMMANDE_PRODUIT.
     *
     * @param id the id of the cOMMANDE_PRODUITDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/commande-produits/{id}")
    public ResponseEntity<Void> deleteCOMMANDE_PRODUIT(@PathVariable Long id) {
        log.debug("REST request to delete COMMANDE_PRODUIT : {}", id);
        cOMMANDE_PRODUITService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
