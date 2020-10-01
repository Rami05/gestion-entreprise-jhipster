package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.VehiculeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Vehicule and its DTO VehiculeDTO.
 */
@Mapper(componentModel = "spring", uses = {ChauffeurMapper.class})
public interface VehiculeMapper extends EntityMapper<VehiculeDTO, Vehicule> {



    default Vehicule fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vehicule vehicule = new Vehicule();
        vehicule.setId(id);
        return vehicule;
    }
}
