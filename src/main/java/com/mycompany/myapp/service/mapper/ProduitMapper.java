package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.ProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Produit and its DTO ProduitDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {



    default Produit fromId(Long id) {
        if (id == null) {
            return null;
        }
        Produit produit = new Produit();
        produit.setId(id);
        return produit;
    }
}
