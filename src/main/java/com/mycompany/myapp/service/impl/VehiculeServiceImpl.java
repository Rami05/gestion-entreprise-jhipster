package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.VehiculeService;
import com.mycompany.myapp.domain.Vehicule;
import com.mycompany.myapp.repository.VehiculeRepository;
import com.mycompany.myapp.service.dto.VehiculeDTO;
import com.mycompany.myapp.service.mapper.VehiculeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Vehicule.
 */
@Service
@Transactional
public class VehiculeServiceImpl implements VehiculeService {

    private final Logger log = LoggerFactory.getLogger(VehiculeServiceImpl.class);

    private final VehiculeRepository vehiculeRepository;

    private final VehiculeMapper vehiculeMapper;

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository, VehiculeMapper vehiculeMapper) {
        this.vehiculeRepository = vehiculeRepository;
        this.vehiculeMapper = vehiculeMapper;
    }

    /**
     * Save a vehicule.
     *
     * @param vehiculeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VehiculeDTO save(VehiculeDTO vehiculeDTO) {
        log.debug("Request to save Vehicule : {}", vehiculeDTO);
        Vehicule vehicule = vehiculeMapper.toEntity(vehiculeDTO);
        vehicule = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDto(vehicule);
    }

    /**
     * Get all the vehicules.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<VehiculeDTO> findAll() {
        log.debug("Request to get all Vehicules");
        return vehiculeRepository.findAllWithEagerRelationships().stream()
            .map(vehiculeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Vehicule with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<VehiculeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return vehiculeRepository.findAllWithEagerRelationships(pageable).map(vehiculeMapper::toDto);
    }
    

    /**
     * Get one vehicule by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VehiculeDTO> findOne(Long id) {
        log.debug("Request to get Vehicule : {}", id);
        return vehiculeRepository.findOneWithEagerRelationships(id)
            .map(vehiculeMapper::toDto);
    }

    /**
     * Delete the vehicule by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vehicule : {}", id);
        vehiculeRepository.deleteById(id);
    }
}
