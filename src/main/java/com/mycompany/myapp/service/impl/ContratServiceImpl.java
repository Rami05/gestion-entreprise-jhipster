package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ContratService;
import com.mycompany.myapp.domain.Contrat;
import com.mycompany.myapp.repository.ContratRepository;
import com.mycompany.myapp.service.dto.ContratDTO;
import com.mycompany.myapp.service.mapper.ContratMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Contrat.
 */
@Service
@Transactional
public class ContratServiceImpl implements ContratService {

    private final Logger log = LoggerFactory.getLogger(ContratServiceImpl.class);

    private final ContratRepository contratRepository;

    private final ContratMapper contratMapper;

    public ContratServiceImpl(ContratRepository contratRepository, ContratMapper contratMapper) {
        this.contratRepository = contratRepository;
        this.contratMapper = contratMapper;
    }

    /**
     * Save a contrat.
     *
     * @param contratDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ContratDTO save(ContratDTO contratDTO) {
        log.debug("Request to save Contrat : {}", contratDTO);
        Contrat contrat = contratMapper.toEntity(contratDTO);
        contrat = contratRepository.save(contrat);
        return contratMapper.toDto(contrat);
    }

    /**
     * Get all the contrats.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContratDTO> findAll() {
        log.debug("Request to get all Contrats");
        return contratRepository.findAll().stream()
            .map(contratMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one contrat by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContratDTO> findOne(Long id) {
        log.debug("Request to get Contrat : {}", id);
        return contratRepository.findById(id)
            .map(contratMapper::toDto);
    }

    /**
     * Delete the contrat by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Contrat : {}", id);
        contratRepository.deleteById(id);
    }
}
