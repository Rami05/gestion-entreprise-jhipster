package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.COMMANDE_PRODUITDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity COMMANDE_PRODUIT and its DTO COMMANDE_PRODUITDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface COMMANDE_PRODUITMapper extends EntityMapper<COMMANDE_PRODUITDTO, COMMANDE_PRODUIT> {



    default COMMANDE_PRODUIT fromId(Long id) {
        if (id == null) {
            return null;
        }
        COMMANDE_PRODUIT cOMMANDE_PRODUIT = new COMMANDE_PRODUIT();
        cOMMANDE_PRODUIT.setId(id);
        return cOMMANDE_PRODUIT;
    }
}
