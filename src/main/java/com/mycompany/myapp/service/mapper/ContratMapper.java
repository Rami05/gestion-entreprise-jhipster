package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ContratDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Contrat and its DTO ContratDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeMapper.class})
public interface ContratMapper extends EntityMapper<ContratDTO, Contrat> {

    @Mapping(source = "employe.id", target = "employeId")
    @Mapping(source = "employe.nom", target = "employeNom")
    ContratDTO toDto(Contrat contrat);

    @Mapping(source = "employeId", target = "employe")
    Contrat toEntity(ContratDTO contratDTO);

    default Contrat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contrat contrat = new Contrat();
        contrat.setId(id);
        return contrat;
    }
}
