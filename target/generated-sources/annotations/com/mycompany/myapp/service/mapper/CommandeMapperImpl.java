package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Commande;
import com.mycompany.myapp.service.dto.CommandeDTO;
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
public class CommandeMapperImpl implements CommandeMapper {

    @Override
    public Commande toEntity(CommandeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Commande commande = new Commande();

        commande.setId( dto.getId() );
        commande.setReference( dto.getReference() );

        return commande;
    }

    @Override
    public CommandeDTO toDto(Commande entity) {
        if ( entity == null ) {
            return null;
        }

        CommandeDTO commandeDTO = new CommandeDTO();

        commandeDTO.setId( entity.getId() );
        commandeDTO.setReference( entity.getReference() );

        return commandeDTO;
    }

    @Override
    public List<Commande> toEntity(List<CommandeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Commande> list = new ArrayList<Commande>( dtoList.size() );
        for ( CommandeDTO commandeDTO : dtoList ) {
            list.add( toEntity( commandeDTO ) );
        }

        return list;
    }

    @Override
    public List<CommandeDTO> toDto(List<Commande> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CommandeDTO> list = new ArrayList<CommandeDTO>( entityList.size() );
        for ( Commande commande : entityList ) {
            list.add( toDto( commande ) );
        }

        return list;
    }
}
