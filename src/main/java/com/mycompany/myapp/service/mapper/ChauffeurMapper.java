package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ChauffeurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Chauffeur and its DTO ChauffeurDTO.
 */
@Mapper(componentModel = "spring", uses = {Permis_de_conduitMapper.class})
public interface ChauffeurMapper extends EntityMapper<ChauffeurDTO, Chauffeur> {

    @Mapping(source = "permis_de_conduit.id", target = "permis_de_conduitId")
    @Mapping(source = "permis_de_conduit.num_permis", target = "permis_de_conduitNum_permis")
    ChauffeurDTO toDto(Chauffeur chauffeur);

    @Mapping(target = "vehicules", ignore = true)
    @Mapping(source = "permis_de_conduitId", target = "permis_de_conduit")
    Chauffeur toEntity(ChauffeurDTO chauffeurDTO);

    default Chauffeur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Chauffeur chauffeur = new Chauffeur();
        chauffeur.setId(id);
        return chauffeur;
    }
}
