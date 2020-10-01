package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Compte_bancaire;
import com.mycompany.myapp.service.dto.Compte_bancaireDTO;
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
public class Compte_bancaireMapperImpl implements Compte_bancaireMapper {

    @Override
    public Compte_bancaire toEntity(Compte_bancaireDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Compte_bancaire compte_bancaire = new Compte_bancaire();

        compte_bancaire.setId( dto.getId() );
        compte_bancaire.setRib( dto.getRib() );
        compte_bancaire.setAgence( dto.getAgence() );

        return compte_bancaire;
    }

    @Override
    public Compte_bancaireDTO toDto(Compte_bancaire entity) {
        if ( entity == null ) {
            return null;
        }

        Compte_bancaireDTO compte_bancaireDTO = new Compte_bancaireDTO();

        compte_bancaireDTO.setId( entity.getId() );
        compte_bancaireDTO.setRib( entity.getRib() );
        compte_bancaireDTO.setAgence( entity.getAgence() );

        return compte_bancaireDTO;
    }

    @Override
    public List<Compte_bancaire> toEntity(List<Compte_bancaireDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Compte_bancaire> list = new ArrayList<Compte_bancaire>( dtoList.size() );
        for ( Compte_bancaireDTO compte_bancaireDTO : dtoList ) {
            list.add( toEntity( compte_bancaireDTO ) );
        }

        return list;
    }

    @Override
    public List<Compte_bancaireDTO> toDto(List<Compte_bancaire> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<Compte_bancaireDTO> list = new ArrayList<Compte_bancaireDTO>( entityList.size() );
        for ( Compte_bancaire compte_bancaire : entityList ) {
            list.add( toDto( compte_bancaire ) );
        }

        return list;
    }
}
