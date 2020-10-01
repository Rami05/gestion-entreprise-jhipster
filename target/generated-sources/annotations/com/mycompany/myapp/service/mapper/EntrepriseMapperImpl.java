package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Entreprise;
import com.mycompany.myapp.service.dto.EntrepriseDTO;
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
public class EntrepriseMapperImpl implements EntrepriseMapper {

    @Override
    public Entreprise toEntity(EntrepriseDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Entreprise entreprise = new Entreprise();

        entreprise.setId( dto.getId() );
        entreprise.setRaison_social( dto.getRaison_social() );
        entreprise.setChiffre_affaire( dto.getChiffre_affaire() );
        entreprise.setGerant( dto.getGerant() );
        entreprise.setSecteur( dto.getSecteur() );
        entreprise.setSiege( dto.getSiege() );

        return entreprise;
    }

    @Override
    public EntrepriseDTO toDto(Entreprise entity) {
        if ( entity == null ) {
            return null;
        }

        EntrepriseDTO entrepriseDTO = new EntrepriseDTO();

        entrepriseDTO.setId( entity.getId() );
        entrepriseDTO.setRaison_social( entity.getRaison_social() );
        entrepriseDTO.setChiffre_affaire( entity.getChiffre_affaire() );
        entrepriseDTO.setGerant( entity.getGerant() );
        entrepriseDTO.setSecteur( entity.getSecteur() );
        entrepriseDTO.setSiege( entity.getSiege() );

        return entrepriseDTO;
    }

    @Override
    public List<Entreprise> toEntity(List<EntrepriseDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Entreprise> list = new ArrayList<Entreprise>( dtoList.size() );
        for ( EntrepriseDTO entrepriseDTO : dtoList ) {
            list.add( toEntity( entrepriseDTO ) );
        }

        return list;
    }

    @Override
    public List<EntrepriseDTO> toDto(List<Entreprise> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EntrepriseDTO> list = new ArrayList<EntrepriseDTO>( entityList.size() );
        for ( Entreprise entreprise : entityList ) {
            list.add( toDto( entreprise ) );
        }

        return list;
    }
}
