package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.Permis_de_conduitDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Permis_de_conduit.
 */
public interface Permis_de_conduitService {

    /**
     * Save a permis_de_conduit.
     *
     * @param permis_de_conduitDTO the entity to save
     * @return the persisted entity
     */
    Permis_de_conduitDTO save(Permis_de_conduitDTO permis_de_conduitDTO);

    /**
     * Get all the permis_de_conduits.
     *
     * @return the list of entities
     */
    List<Permis_de_conduitDTO> findAll();
    /**
     * Get all the Permis_de_conduitDTO where Chauffeur is null.
     *
     * @return the list of entities
     */
    List<Permis_de_conduitDTO> findAllWhereChauffeurIsNull();


    /**
     * Get the "id" permis_de_conduit.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Permis_de_conduitDTO> findOne(Long id);

    /**
     * Delete the "id" permis_de_conduit.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
