package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Produit;
import com.mycompany.myapp.service.dto.ProduitDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-01T16:31:05+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_265 (Private Build)"
)
@Component
public class ProduitMapperImpl implements ProduitMapper {

    @Override
    public Produit toEntity(ProduitDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Produit produit = new Produit();

        produit.setId( dto.getId() );
        produit.setLibelle( dto.getLibelle() );

        return produit;
    }

    @Override
    public ProduitDTO toDto(Produit entity) {
        if ( entity == null ) {
            return null;
        }

        ProduitDTO produitDTO = new ProduitDTO();

        produitDTO.setId( entity.getId() );
        produitDTO.setLibelle( entity.getLibelle() );

        return produitDTO;
    }

    @Override
    public List<Produit> toEntity(List<ProduitDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Produit> list = new ArrayList<Produit>( dtoList.size() );
        for ( ProduitDTO produitDTO : dtoList ) {
            list.add( toEntity( produitDTO ) );
        }

        return list;
    }

    @Override
    public List<ProduitDTO> toDto(List<Produit> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProduitDTO> list = new ArrayList<ProduitDTO>( entityList.size() );
        for ( Produit produit : entityList ) {
            list.add( toDto( produit ) );
        }

        return list;
    }
}
