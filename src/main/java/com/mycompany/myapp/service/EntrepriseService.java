package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EntrepriseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Entreprise.
 */
public interface EntrepriseService {

    /**
     * Save a entreprise.
     *
     * @param entrepriseDTO the entity to save
     * @return the persisted entity
     */
    EntrepriseDTO save(EntrepriseDTO entrepriseDTO);

    /**
     * Get all the entreprises.
     *
     * @return the list of entities
     */
    List<EntrepriseDTO> findAll();


    /**
     * Get the "id" entreprise.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EntrepriseDTO> findOne(Long id);

    /**
     * Delete the "id" entreprise.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
