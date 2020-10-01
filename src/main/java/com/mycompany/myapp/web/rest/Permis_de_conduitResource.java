package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.Permis_de_conduitService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.Permis_de_conduitDTO;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing Permis_de_conduit.
 */
@RestController
@RequestMapping("/api")
public class Permis_de_conduitResource {

    private final Logger log = LoggerFactory.getLogger(Permis_de_conduitResource.class);

    private static final String ENTITY_NAME = "permis_de_conduit";

    private final Permis_de_conduitService permis_de_conduitService;

    public Permis_de_conduitResource(Permis_de_conduitService permis_de_conduitService) {
        this.permis_de_conduitService = permis_de_conduitService;
    }

    /**
     * POST  /permis-de-conduits : Create a new permis_de_conduit.
     *
     * @param permis_de_conduitDTO the permis_de_conduitDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new permis_de_conduitDTO, or with status 400 (Bad Request) if the permis_de_conduit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/permis-de-conduits")
    public ResponseEntity<Permis_de_conduitDTO> createPermis_de_conduit(@Valid @RequestBody Permis_de_conduitDTO permis_de_conduitDTO) throws URISyntaxException {
        log.debug("REST request to save Permis_de_conduit : {}", permis_de_conduitDTO);
        if (permis_de_conduitDTO.getId() != null) {
            throw new BadRequestAlertException("A new permis_de_conduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Permis_de_conduitDTO result = permis_de_conduitService.save(permis_de_conduitDTO);
        return ResponseEntity.created(new URI("/api/permis-de-conduits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /permis-de-conduits : Updates an existing permis_de_conduit.
     *
     * @param permis_de_conduitDTO the permis_de_conduitDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated permis_de_conduitDTO,
     * or with status 400 (Bad Request) if the permis_de_conduitDTO is not valid,
     * or with status 500 (Internal Server Error) if the permis_de_conduitDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/permis-de-conduits")
    public ResponseEntity<Permis_de_conduitDTO> updatePermis_de_conduit(@Valid @RequestBody Permis_de_conduitDTO permis_de_conduitDTO) throws URISyntaxException {
        log.debug("REST request to update Permis_de_conduit : {}", permis_de_conduitDTO);
        if (permis_de_conduitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Permis_de_conduitDTO result = permis_de_conduitService.save(permis_de_conduitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, permis_de_conduitDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /permis-de-conduits : get all the permis_de_conduits.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of permis_de_conduits in body
     */
    @GetMapping("/permis-de-conduits")
    public List<Permis_de_conduitDTO> getAllPermis_de_conduits(@RequestParam(required = false) String filter) {
        if ("chauffeur-is-null".equals(filter)) {
            log.debug("REST request to get all Permis_de_conduits where chauffeur is null");
            return permis_de_conduitService.findAllWhereChauffeurIsNull();
        }
        log.debug("REST request to get all Permis_de_conduits");
        return permis_de_conduitService.findAll();
    }

    /**
     * GET  /permis-de-conduits/:id : get the "id" permis_de_conduit.
     *
     * @param id the id of the permis_de_conduitDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the permis_de_conduitDTO, or with status 404 (Not Found)
     */
    @GetMapping("/permis-de-conduits/{id}")
    public ResponseEntity<Permis_de_conduitDTO> getPermis_de_conduit(@PathVariable Long id) {
        log.debug("REST request to get Permis_de_conduit : {}", id);
        Optional<Permis_de_conduitDTO> permis_de_conduitDTO = permis_de_conduitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(permis_de_conduitDTO);
    }

    /**
     * DELETE  /permis-de-conduits/:id : delete the "id" permis_de_conduit.
     *
     * @param id the id of the permis_de_conduitDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/permis-de-conduits/{id}")
    public ResponseEntity<Void> deletePermis_de_conduit(@PathVariable Long id) {
        log.debug("REST request to delete Permis_de_conduit : {}", id);
        permis_de_conduitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
