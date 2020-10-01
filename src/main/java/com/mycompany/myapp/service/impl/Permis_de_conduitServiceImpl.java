package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.Permis_de_conduitService;
import com.mycompany.myapp.domain.Permis_de_conduit;
import com.mycompany.myapp.repository.Permis_de_conduitRepository;
import com.mycompany.myapp.service.dto.Permis_de_conduitDTO;
import com.mycompany.myapp.service.mapper.Permis_de_conduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Permis_de_conduit.
 */
@Service
@Transactional
public class Permis_de_conduitServiceImpl implements Permis_de_conduitService {

    private final Logger log = LoggerFactory.getLogger(Permis_de_conduitServiceImpl.class);

    private final Permis_de_conduitRepository permis_de_conduitRepository;

    private final Permis_de_conduitMapper permis_de_conduitMapper;

    public Permis_de_conduitServiceImpl(Permis_de_conduitRepository permis_de_conduitRepository, Permis_de_conduitMapper permis_de_conduitMapper) {
        this.permis_de_conduitRepository = permis_de_conduitRepository;
        this.permis_de_conduitMapper = permis_de_conduitMapper;
    }

    /**
     * Save a permis_de_conduit.
     *
     * @param permis_de_conduitDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Permis_de_conduitDTO save(Permis_de_conduitDTO permis_de_conduitDTO) {
        log.debug("Request to save Permis_de_conduit : {}", permis_de_conduitDTO);
        Permis_de_conduit permis_de_conduit = permis_de_conduitMapper.toEntity(permis_de_conduitDTO);
        permis_de_conduit = permis_de_conduitRepository.save(permis_de_conduit);
        return permis_de_conduitMapper.toDto(permis_de_conduit);
    }

    /**
     * Get all the permis_de_conduits.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Permis_de_conduitDTO> findAll() {
        log.debug("Request to get all Permis_de_conduits");
        return permis_de_conduitRepository.findAll().stream()
            .map(permis_de_conduitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  get all the permis_de_conduits where Chauffeur is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Permis_de_conduitDTO> findAllWhereChauffeurIsNull() {
        log.debug("Request to get all permis_de_conduits where Chauffeur is null");
        return StreamSupport
            .stream(permis_de_conduitRepository.findAll().spliterator(), false)
            .filter(permis_de_conduit -> permis_de_conduit.getChauffeur() == null)
            .map(permis_de_conduitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one permis_de_conduit by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Permis_de_conduitDTO> findOne(Long id) {
        log.debug("Request to get Permis_de_conduit : {}", id);
        return permis_de_conduitRepository.findById(id)
            .map(permis_de_conduitMapper::toDto);
    }

    /**
     * Delete the permis_de_conduit by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Permis_de_conduit : {}", id);
        permis_de_conduitRepository.deleteById(id);
    }
}
