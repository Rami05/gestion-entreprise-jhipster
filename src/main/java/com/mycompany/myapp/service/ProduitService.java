package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ProduitDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Produit.
 */
public interface ProduitService {

    /**
     * Save a produit.
     *
     * @param produitDTO the entity to save
     * @return the persisted entity
     */
    ProduitDTO save(ProduitDTO produitDTO);

    /**
     * Get all the produits.
     *
     * @return the list of entities
     */
    List<ProduitDTO> findAll();


    /**
     * Get the "id" produit.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProduitDTO> findOne(Long id);

    /**
     * Delete the "id" produit.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}