package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.Compte_bancaireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Compte_bancaire and its DTO Compte_bancaireDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Compte_bancaireMapper extends EntityMapper<Compte_bancaireDTO, Compte_bancaire> {



    default Compte_bancaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        Compte_bancaire compte_bancaire = new Compte_bancaire();
        compte_bancaire.setId(id);
        return compte_bancaire;
    }
}
