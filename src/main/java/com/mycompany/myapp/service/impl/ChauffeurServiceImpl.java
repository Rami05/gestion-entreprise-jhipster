package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ChauffeurService;
import com.mycompany.myapp.domain.Chauffeur;
import com.mycompany.myapp.repository.ChauffeurRepository;
import com.mycompany.myapp.service.dto.ChauffeurDTO;
import com.mycompany.myapp.service.mapper.ChauffeurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Chauffeur.
 */
@Service
@Transactional
public class ChauffeurServiceImpl implements ChauffeurService {

    private final Logger log = LoggerFactory.getLogger(ChauffeurServiceImpl.class);

    private final ChauffeurRepository chauffeurRepository;

    private final ChauffeurMapper chauffeurMapper;

    public ChauffeurServiceImpl(ChauffeurRepository chauffeurRepository, ChauffeurMapper chauffeurMapper) {
        this.chauffeurRepository = chauffeurRepository;
        this.chauffeurMapper = chauffeurMapper;
    }

    /**
     * Save a chauffeur.
     *
     * @param chauffeurDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ChauffeurDTO save(ChauffeurDTO chauffeurDTO) {
        log.debug("Request to save Chauffeur : {}", chauffeurDTO);
        Chauffeur chauffeur = chauffeurMapper.toEntity(chauffeurDTO);
        chauffeur = chauffeurRepository.save(chauffeur);
        return chauffeurMapper.toDto(chauffeur);
    }

    /**
     * Get all the chauffeurs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ChauffeurDTO> findAll() {
        log.debug("Request to get all Chauffeurs");
        return chauffeurRepository.findAll().stream()
            .map(chauffeurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one chauffeur by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ChauffeurDTO> findOne(Long id) {
        log.debug("Request to get Chauffeur : {}", id);
        return chauffeurRepository.findById(id)
            .map(chauffeurMapper::toDto);
    }

    /**
     * Delete the chauffeur by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Chauffeur : {}", id);
        chauffeurRepository.deleteById(id);
    }
}
