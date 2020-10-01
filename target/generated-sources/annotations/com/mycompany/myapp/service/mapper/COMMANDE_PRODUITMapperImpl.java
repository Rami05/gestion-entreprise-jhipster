package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.COMMANDE_PRODUIT;
import com.mycompany.myapp.service.dto.COMMANDE_PRODUITDTO;
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
public class COMMANDE_PRODUITMapperImpl implements COMMANDE_PRODUITMapper {

    @Override
    public COMMANDE_PRODUIT toEntity(COMMANDE_PRODUITDTO dto) {
        if ( dto == null ) {
            return null;
        }

        COMMANDE_PRODUIT cOMMANDE_PRODUIT = new COMMANDE_PRODUIT();

        cOMMANDE_PRODUIT.setId( dto.getId() );
        cOMMANDE_PRODUIT.setDateCreation( dto.getDateCreation() );
        cOMMANDE_PRODUIT.setDateModification( dto.getDateModification() );
        cOMMANDE_PRODUIT.setEtat( dto.getEtat() );

        return cOMMANDE_PRODUIT;
    }

    @Override
    public COMMANDE_PRODUITDTO toDto(COMMANDE_PRODUIT entity) {
        if ( entity == null ) {
            return null;
        }

        COMMANDE_PRODUITDTO cOMMANDE_PRODUITDTO = new COMMANDE_PRODUITDTO();

        cOMMANDE_PRODUITDTO.setId( entity.getId() );
        cOMMANDE_PRODUITDTO.setDateCreation( entity.getDateCreation() );
        cOMMANDE_PRODUITDTO.setDateModification( entity.getDateModification() );
        cOMMANDE_PRODUITDTO.setEtat( entity.getEtat() );

        return cOMMANDE_PRODUITDTO;
    }

    @Override
    public List<COMMANDE_PRODUIT> toEntity(List<COMMANDE_PRODUITDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<COMMANDE_PRODUIT> list = new ArrayList<COMMANDE_PRODUIT>( dtoList.size() );
        for ( COMMANDE_PRODUITDTO cOMMANDE_PRODUITDTO : dtoList ) {
            list.add( toEntity( cOMMANDE_PRODUITDTO ) );
        }

        return list;
    }

    @Override
    public List<COMMANDE_PRODUITDTO> toDto(List<COMMANDE_PRODUIT> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<COMMANDE_PRODUITDTO> list = new ArrayList<COMMANDE_PRODUITDTO>( entityList.size() );
        for ( COMMANDE_PRODUIT cOMMANDE_PRODUIT : entityList ) {
            list.add( toDto( cOMMANDE_PRODUIT ) );
        }

        return list;
    }
}
