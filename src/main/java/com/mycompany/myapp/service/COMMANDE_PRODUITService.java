package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.COMMANDE_PRODUITDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing COMMANDE_PRODUIT.
 */
public interface COMMANDE_PRODUITService {

    /**
     * Save a cOMMANDE_PRODUIT.
     *
     * @param cOMMANDE_PRODUITDTO the entity to save
     * @return the persisted entity
     */
    COMMANDE_PRODUITDTO save(COMMANDE_PRODUITDTO cOMMANDE_PRODUITDTO);

    /**
     * Get all the cOMMANDE_PRODUITS.
     *
     * @return the list of entities
     */
    List<COMMANDE_PRODUITDTO> findAll();


    /**
     * Get the "id" cOMMANDE_PRODUIT.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<COMMANDE_PRODUITDTO> findOne(Long id);

    /**
     * Delete the "id" cOMMANDE_PRODUIT.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
