package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.Compte_bancaireService;
import com.mycompany.myapp.domain.Compte_bancaire;
import com.mycompany.myapp.repository.Compte_bancaireRepository;
import com.mycompany.myapp.service.dto.Compte_bancaireDTO;
import com.mycompany.myapp.service.mapper.Compte_bancaireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Compte_bancaire.
 */
@Service
@Transactional
public class Compte_bancaireServiceImpl implements Compte_bancaireService {

    private final Logger log = LoggerFactory.getLogger(Compte_bancaireServiceImpl.class);

    private final Compte_bancaireRepository compte_bancaireRepository;

    private final Compte_bancaireMapper compte_bancaireMapper;

    public Compte_bancaireServiceImpl(Compte_bancaireRepository compte_bancaireRepository, Compte_bancaireMapper compte_bancaireMapper) {
        this.compte_bancaireRepository = compte_bancaireRepository;
        this.compte_bancaireMapper = compte_bancaireMapper;
    }

    /**
     * Save a compte_bancaire.
     *
     * @param compte_bancaireDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Compte_bancaireDTO save(Compte_bancaireDTO compte_bancaireDTO) {
        log.debug("Request to save Compte_bancaire : {}", compte_bancaireDTO);
        Compte_bancaire compte_bancaire = compte_bancaireMapper.toEntity(compte_bancaireDTO);
        compte_bancaire = compte_bancaireRepository.save(compte_bancaire);
        return compte_bancaireMapper.toDto(compte_bancaire);
    }

    /**
     * Get all the compte_bancaires.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Compte_bancaireDTO> findAll() {
        log.debug("Request to get all Compte_bancaires");
        return compte_bancaireRepository.findAll().stream()
            .map(compte_bancaireMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one compte_bancaire by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Compte_bancaireDTO> findOne(Long id) {
        log.debug("Request to get Compte_bancaire : {}", id);
        return compte_bancaireRepository.findById(id)
            .map(compte_bancaireMapper::toDto);
    }

    /**
     * Delete the compte_bancaire by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Compte_bancaire : {}", id);
        compte_bancaireRepository.deleteById(id);
    }
}
