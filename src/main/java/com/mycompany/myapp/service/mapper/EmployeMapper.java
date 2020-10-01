package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EmployeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Employe and its DTO EmployeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmployeMapper extends EntityMapper<EmployeDTO, Employe> {



    default Employe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employe employe = new Employe();
        employe.setId(id);
        return employe;
    }
}
