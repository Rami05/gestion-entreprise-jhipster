package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EmployeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Employe.
 */
public interface EmployeService {

    /**
     * Save a employe.
     *
     * @param employeDTO the entity to save
     * @return the persisted entity
     */
    EmployeDTO save(EmployeDTO employeDTO);

    /**
     * Get all the employes.
     *
     * @return the list of entities
     */
    List<EmployeDTO> findAll();


    /**
     * Get the "id" employe.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EmployeDTO> findOne(Long id);

    /**
     * Delete the "id" employe.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
