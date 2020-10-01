package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.Compte_bancaireDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Compte_bancaire.
 */
public interface Compte_bancaireService {

    /**
     * Save a compte_bancaire.
     *
     * @param compte_bancaireDTO the entity to save
     * @return the persisted entity
     */
    Compte_bancaireDTO save(Compte_bancaireDTO compte_bancaireDTO);

    /**
     * Get all the compte_bancaires.
     *
     * @return the list of entities
     */
    List<Compte_bancaireDTO> findAll();


    /**
     * Get the "id" compte_bancaire.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Compte_bancaireDTO> findOne(Long id);

    /**
     * Delete the "id" compte_bancaire.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
