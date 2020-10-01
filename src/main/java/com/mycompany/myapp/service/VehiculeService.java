package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.VehiculeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Vehicule.
 */
public interface VehiculeService {

    /**
     * Save a vehicule.
     *
     * @param vehiculeDTO the entity to save
     * @return the persisted entity
     */
    VehiculeDTO save(VehiculeDTO vehiculeDTO);

    /**
     * Get all the vehicules.
     *
     * @return the list of entities
     */
    List<VehiculeDTO> findAll();

    /**
     * Get all the Vehicule with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<VehiculeDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" vehicule.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VehiculeDTO> findOne(Long id);

    /**
     * Delete the "id" vehicule.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
