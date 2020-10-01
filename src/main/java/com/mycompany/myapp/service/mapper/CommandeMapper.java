package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CommandeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Commande and its DTO CommandeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommandeMapper extends EntityMapper<CommandeDTO, Commande> {



    default Commande fromId(Long id) {
        if (id == null) {
            return null;
        }
        Commande commande = new Commande();
        commande.setId(id);
        return commande;
    }
}
