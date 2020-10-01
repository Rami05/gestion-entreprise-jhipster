package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Chauffeur;
import com.mycompany.myapp.domain.Vehicule;
import com.mycompany.myapp.service.dto.ChauffeurDTO;
import com.mycompany.myapp.service.dto.VehiculeDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-01T16:31:05+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_265 (Private Build)"
)
@Component
public class VehiculeMapperImpl implements VehiculeMapper {

    @Autowired
    private ChauffeurMapper chauffeurMapper;

    @Override
    public Vehicule toEntity(VehiculeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Vehicule vehicule = new Vehicule();

        vehicule.setId( dto.getId() );
        vehicule.setIdentifiant( dto.getIdentifiant() );
        vehicule.setChauffeurs( chauffeurDTOSetToChauffeurSet( dto.getChauffeurs() ) );

        return vehicule;
    }

    @Override
    public VehiculeDTO toDto(Vehicule entity) {
        if ( entity == null ) {
            return null;
        }

        VehiculeDTO vehiculeDTO = new VehiculeDTO();

        vehiculeDTO.setId( entity.getId() );
        vehiculeDTO.setIdentifiant( entity.getIdentifiant() );
        vehiculeDTO.setChauffeurs( chauffeurSetToChauffeurDTOSet( entity.getChauffeurs() ) );

        return vehiculeDTO;
    }

    @Override
    public List<Vehicule> toEntity(List<VehiculeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Vehicule> list = new ArrayList<Vehicule>( dtoList.size() );
        for ( VehiculeDTO vehiculeDTO : dtoList ) {
            list.add( toEntity( vehiculeDTO ) );
        }

        return list;
    }

    @Override
    public List<VehiculeDTO> toDto(List<Vehicule> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<VehiculeDTO> list = new ArrayList<VehiculeDTO>( entityList.size() );
        for ( Vehicule vehicule : entityList ) {
            list.add( toDto( vehicule ) );
        }

        return list;
    }

    protected Set<Chauffeur> chauffeurDTOSetToChauffeurSet(Set<ChauffeurDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Chauffeur> set1 = new HashSet<Chauffeur>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ChauffeurDTO chauffeurDTO : set ) {
            set1.add( chauffeurMapper.toEntity( chauffeurDTO ) );
        }

        return set1;
    }

    protected Set<ChauffeurDTO> chauffeurSetToChauffeurDTOSet(Set<Chauffeur> set) {
        if ( set == null ) {
            return null;
        }

        Set<ChauffeurDTO> set1 = new HashSet<ChauffeurDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Chauffeur chauffeur : set ) {
            set1.add( chauffeurMapper.toDto( chauffeur ) );
        }

        return set1;
    }
}
