package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.Permis_de_conduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Permis_de_conduit and its DTO Permis_de_conduitDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Permis_de_conduitMapper extends EntityMapper<Permis_de_conduitDTO, Permis_de_conduit> {


    @Mapping(target = "chauffeur", ignore = true)
    Permis_de_conduit toEntity(Permis_de_conduitDTO permis_de_conduitDTO);

    default Permis_de_conduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        Permis_de_conduit permis_de_conduit = new Permis_de_conduit();
        permis_de_conduit.setId(id);
        return permis_de_conduit;
    }
}
