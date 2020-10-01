package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.COMMANDE_PRODUITService;
import com.mycompany.myapp.domain.COMMANDE_PRODUIT;
import com.mycompany.myapp.repository.COMMANDE_PRODUITRepository;
import com.mycompany.myapp.service.dto.COMMANDE_PRODUITDTO;
import com.mycompany.myapp.service.mapper.COMMANDE_PRODUITMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing COMMANDE_PRODUIT.
 */
@Service
@Transactional
public class COMMANDE_PRODUITServiceImpl implements COMMANDE_PRODUITService {

    private final Logger log = LoggerFactory.getLogger(COMMANDE_PRODUITServiceImpl.class);

    private final COMMANDE_PRODUITRepository cOMMANDE_PRODUITRepository;

    private final COMMANDE_PRODUITMapper cOMMANDE_PRODUITMapper;

    public COMMANDE_PRODUITServiceImpl(COMMANDE_PRODUITRepository cOMMANDE_PRODUITRepository, COMMANDE_PRODUITMapper cOMMANDE_PRODUITMapper) {
        this.cOMMANDE_PRODUITRepository = cOMMANDE_PRODUITRepository;
        this.cOMMANDE_PRODUITMapper = cOMMANDE_PRODUITMapper;
    }

    /**
     * Save a cOMMANDE_PRODUIT.
     *
     * @param cOMMANDE_PRODUITDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public COMMANDE_PRODUITDTO save(COMMANDE_PRODUITDTO cOMMANDE_PRODUITDTO) {
        log.debug("Request to save COMMANDE_PRODUIT : {}", cOMMANDE_PRODUITDTO);
        COMMANDE_PRODUIT cOMMANDE_PRODUIT = cOMMANDE_PRODUITMapper.toEntity(cOMMANDE_PRODUITDTO);
        cOMMANDE_PRODUIT = cOMMANDE_PRODUITRepository.save(cOMMANDE_PRODUIT);
        return cOMMANDE_PRODUITMapper.toDto(cOMMANDE_PRODUIT);
    }

    /**
     * Get all the cOMMANDE_PRODUITS.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<COMMANDE_PRODUITDTO> findAll() {
        log.debug("Request to get all COMMANDE_PRODUITS");
        return cOMMANDE_PRODUITRepository.findAll().stream()
            .map(cOMMANDE_PRODUITMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one cOMMANDE_PRODUIT by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<COMMANDE_PRODUITDTO> findOne(Long id) {
        log.debug("Request to get COMMANDE_PRODUIT : {}", id);
        return cOMMANDE_PRODUITRepository.findById(id)
            .map(cOMMANDE_PRODUITMapper::toDto);
    }

    /**
     * Delete the cOMMANDE_PRODUIT by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete COMMANDE_PRODUIT : {}", id);
        cOMMANDE_PRODUITRepository.deleteById(id);
    }
}
