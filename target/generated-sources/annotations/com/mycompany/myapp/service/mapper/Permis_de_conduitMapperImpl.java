package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Permis_de_conduit;
import com.mycompany.myapp.service.dto.Permis_de_conduitDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-01T16:31:05+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_265 (Private Build)"
)
@Component
public class Permis_de_conduitMapperImpl implements Permis_de_conduitMapper {

    @Override
    public Permis_de_conduitDTO toDto(Permis_de_conduit entity) {
        if ( entity == null ) {
            return null;
        }

        Permis_de_conduitDTO permis_de_conduitDTO = new Permis_de_conduitDTO();

        permis_de_conduitDTO.setId( entity.getId() );
        permis_de_conduitDTO.setNum_permis( entity.getNum_permis() );
        byte[] copie_permis = entity.getCopie_permis();
        if ( copie_permis != null ) {
            permis_de_conduitDTO.setCopie_permis( Arrays.copyOf( copie_permis, copie_permis.length ) );
        }
        permis_de_conduitDTO.setCopie_permisContentType( entity.getCopie_permisContentType() );

        return permis_de_conduitDTO;
    }

    @Override
    public List<Permis_de_conduit> toEntity(List<Permis_de_conduitDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Permis_de_conduit> list = new ArrayList<Permis_de_conduit>( dtoList.size() );
        for ( Permis_de_conduitDTO permis_de_conduitDTO : dtoList ) {
            list.add( toEntity( permis_de_conduitDTO ) );
        }

        return list;
    }

    @Override
    public List<Permis_de_conduitDTO> toDto(List<Permis_de_conduit> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<Permis_de_conduitDTO> list = new ArrayList<Permis_de_conduitDTO>( entityList.size() );
        for ( Permis_de_conduit permis_de_conduit : entityList ) {
            list.add( toDto( permis_de_conduit ) );
        }

        return list;
    }

    @Override
    public Permis_de_conduit toEntity(Permis_de_conduitDTO permis_de_conduitDTO) {
        if ( permis_de_conduitDTO == null ) {
            return null;
        }

        Permis_de_conduit permis_de_conduit = new Permis_de_conduit();

        permis_de_conduit.setId( permis_de_conduitDTO.getId() );
        permis_de_conduit.setNum_permis( permis_de_conduitDTO.getNum_permis() );
        byte[] copie_permis = permis_de_conduitDTO.getCopie_permis();
        if ( copie_permis != null ) {
            permis_de_conduit.setCopie_permis( Arrays.copyOf( copie_permis, copie_permis.length ) );
        }
        permis_de_conduit.setCopie_permisContentType( permis_de_conduitDTO.getCopie_permisContentType() );

        return permis_de_conduit;
    }
}
