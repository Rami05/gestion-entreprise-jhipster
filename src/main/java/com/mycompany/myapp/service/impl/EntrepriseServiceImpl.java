package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EntrepriseService;
import com.mycompany.myapp.domain.Entreprise;
import com.mycompany.myapp.repository.EntrepriseRepository;
import com.mycompany.myapp.service.dto.EntrepriseDTO;
import com.mycompany.myapp.service.mapper.EntrepriseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Entreprise.
 */
@Service
@Transactional
public class EntrepriseServiceImpl implements EntrepriseService {

    private final Logger log = LoggerFactory.getLogger(EntrepriseServiceImpl.class);

    private final EntrepriseRepository entrepriseRepository;

    private final EntrepriseMapper entrepriseMapper;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, EntrepriseMapper entrepriseMapper) {
        this.entrepriseRepository = entrepriseRepository;
        this.entrepriseMapper = entrepriseMapper;
    }

    /**
     * Save a entreprise.
     *
     * @param entrepriseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EntrepriseDTO save(EntrepriseDTO entrepriseDTO) {
        log.debug("Request to save Entreprise : {}", entrepriseDTO);
        Entreprise entreprise = entrepriseMapper.toEntity(entrepriseDTO);
        entreprise = entrepriseRepository.save(entreprise);
        return entrepriseMapper.toDto(entreprise);
    }

    /**
     * Get all the entreprises.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntrepriseDTO> findAll() {
        log.debug("Request to get all Entreprises");
        return entrepriseRepository.findAll().stream()
            .map(entrepriseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one entreprise by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntrepriseDTO> findOne(Long id) {
        log.debug("Request to get Entreprise : {}", id);
        return entrepriseRepository.findById(id)
            .map(entrepriseMapper::toDto);
    }

    /**
     * Delete the entreprise by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Entreprise : {}", id);
        entrepriseRepository.deleteById(id);
    }
}
