package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Chauffeur;
import com.mycompany.myapp.domain.Permis_de_conduit;
import com.mycompany.myapp.service.dto.ChauffeurDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-01T16:31:05+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_265 (Private Build)"
)
@Component
public class ChauffeurMapperImpl implements ChauffeurMapper {

    @Autowired
    private Permis_de_conduitMapper permis_de_conduitMapper;

    @Override
    public List<Chauffeur> toEntity(List<ChauffeurDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Chauffeur> list = new ArrayList<Chauffeur>( dtoList.size() );
        for ( ChauffeurDTO chauffeurDTO : dtoList ) {
            list.add( toEntity( chauffeurDTO ) );
        }

        return list;
    }

    @Override
    public List<ChauffeurDTO> toDto(List<Chauffeur> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ChauffeurDTO> list = new ArrayList<ChauffeurDTO>( entityList.size() );
        for ( Chauffeur chauffeur : entityList ) {
            list.add( toDto( chauffeur ) );
        }

        return list;
    }

    @Override
    public ChauffeurDTO toDto(Chauffeur chauffeur) {
        if ( chauffeur == null ) {
            return null;
        }

        ChauffeurDTO chauffeurDTO = new ChauffeurDTO();

        Long id = chauffeurPermis_de_conduitId( chauffeur );
        if ( id != null ) {
            chauffeurDTO.setPermis_de_conduitId( id );
        }
        String num_permis = chauffeurPermis_de_conduitNum_permis( chauffeur );
        if ( num_permis != null ) {
            chauffeurDTO.setPermis_de_conduitNum_permis( num_permis );
        }
        chauffeurDTO.setId( chauffeur.getId() );
        chauffeurDTO.setIdentite( chauffeur.getIdentite() );
        chauffeurDTO.setCin( chauffeur.getCin() );

        return chauffeurDTO;
    }

    @Override
    public Chauffeur toEntity(ChauffeurDTO chauffeurDTO) {
        if ( chauffeurDTO == null ) {
            return null;
        }

        Chauffeur chauffeur = new Chauffeur();

        chauffeur.setPermis_de_conduit( permis_de_conduitMapper.fromId( chauffeurDTO.getPermis_de_conduitId() ) );
        chauffeur.setId( chauffeurDTO.getId() );
        chauffeur.setIdentite( chauffeurDTO.getIdentite() );
        chauffeur.setCin( chauffeurDTO.getCin() );

        return chauffeur;
    }

    private Long chauffeurPermis_de_conduitId(Chauffeur chauffeur) {
        if ( chauffeur == null ) {
            return null;
        }
        Permis_de_conduit permis_de_conduit = chauffeur.getPermis_de_conduit();
        if ( permis_de_conduit == null ) {
            return null;
        }
        Long id = permis_de_conduit.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String chauffeurPermis_de_conduitNum_permis(Chauffeur chauffeur) {
        if ( chauffeur == null ) {
            return null;
        }
        Permis_de_conduit permis_de_conduit = chauffeur.getPermis_de_conduit();
        if ( permis_de_conduit == null ) {
            return null;
        }
        String num_permis = permis_de_conduit.getNum_permis();
        if ( num_permis == null ) {
            return null;
        }
        return num_permis;
    }
}
