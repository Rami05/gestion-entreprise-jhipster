package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.service.VehiculeService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.service.dto.VehiculeDTO;
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
 * REST controller for managing Vehicule.
 */
@RestController
@RequestMapping("/api")
public class VehiculeResource {

    private final Logger log = LoggerFactory.getLogger(VehiculeResource.class);

    private static final String ENTITY_NAME = "vehicule";

    private final VehiculeService vehiculeService;

    public VehiculeResource(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    /**
     * POST  /vehicules : Create a new vehicule.
     *
     * @param vehiculeDTO the vehiculeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vehiculeDTO, or with status 400 (Bad Request) if the vehicule has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vehicules")
    public ResponseEntity<VehiculeDTO> createVehicule(@RequestBody VehiculeDTO vehiculeDTO) throws URISyntaxException {
        log.debug("REST request to save Vehicule : {}", vehiculeDTO);
        if (vehiculeDTO.getId() != null) {
            throw new BadRequestAlertException("A new vehicule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VehiculeDTO result = vehiculeService.save(vehiculeDTO);
        return ResponseEntity.created(new URI("/api/vehicules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vehicules : Updates an existing vehicule.
     *
     * @param vehiculeDTO the vehiculeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vehiculeDTO,
     * or with status 400 (Bad Request) if the vehiculeDTO is not valid,
     * or with status 500 (Internal Server Error) if the vehiculeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vehicules")
    public ResponseEntity<VehiculeDTO> updateVehicule(@RequestBody VehiculeDTO vehiculeDTO) throws URISyntaxException {
        log.debug("REST request to update Vehicule : {}", vehiculeDTO);
        if (vehiculeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VehiculeDTO result = vehiculeService.save(vehiculeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vehiculeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vehicules : get all the vehicules.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of vehicules in body
     */
    @GetMapping("/vehicules")
    public List<VehiculeDTO> getAllVehicules(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Vehicules");
        return vehiculeService.findAll();
    }

    /**
     * GET  /vehicules/:id : get the "id" vehicule.
     *
     * @param id the id of the vehiculeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vehiculeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vehicules/{id}")
    public ResponseEntity<VehiculeDTO> getVehicule(@PathVariable Long id) {
        log.debug("REST request to get Vehicule : {}", id);
        Optional<VehiculeDTO> vehiculeDTO = vehiculeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vehiculeDTO);
    }

    /**
     * DELETE  /vehicules/:id : delete the "id" vehicule.
     *
     * @param id the id of the vehiculeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vehicules/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        log.debug("REST request to delete Vehicule : {}", id);
        vehiculeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
