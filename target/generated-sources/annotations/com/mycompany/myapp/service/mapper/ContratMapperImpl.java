package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Contrat;
import com.mycompany.myapp.domain.Employe;
import com.mycompany.myapp.service.dto.ContratDTO;
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
public class ContratMapperImpl implements ContratMapper {

    @Autowired
    private EmployeMapper employeMapper;

    @Override
    public List<Contrat> toEntity(List<ContratDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Contrat> list = new ArrayList<Contrat>( dtoList.size() );
        for ( ContratDTO contratDTO : dtoList ) {
            list.add( toEntity( contratDTO ) );
        }

        return list;
    }

    @Override
    public List<ContratDTO> toDto(List<Contrat> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ContratDTO> list = new ArrayList<ContratDTO>( entityList.size() );
        for ( Contrat contrat : entityList ) {
            list.add( toDto( contrat ) );
        }

        return list;
    }

    @Override
    public ContratDTO toDto(Contrat contrat) {
        if ( contrat == null ) {
            return null;
        }

        ContratDTO contratDTO = new ContratDTO();

        String nom = contratEmployeNom( contrat );
        if ( nom != null ) {
            contratDTO.setEmployeNom( nom );
        }
        Long id = contratEmployeId( contrat );
        if ( id != null ) {
            contratDTO.setEmployeId( id );
        }
        contratDTO.setId( contrat.getId() );
        contratDTO.setReference( contrat.getReference() );

        return contratDTO;
    }

    @Override
    public Contrat toEntity(ContratDTO contratDTO) {
        if ( contratDTO == null ) {
            return null;
        }

        Contrat contrat = new Contrat();

        contrat.setEmploye( employeMapper.fromId( contratDTO.getEmployeId() ) );
        contrat.setId( contratDTO.getId() );
        contrat.setReference( contratDTO.getReference() );

        return contrat;
    }

    private String contratEmployeNom(Contrat contrat) {
        if ( contrat == null ) {
            return null;
        }
        Employe employe = contrat.getEmploye();
        if ( employe == null ) {
            return null;
        }
        String nom = employe.getNom();
        if ( nom == null ) {
            return null;
        }
        return nom;
    }

    private Long contratEmployeId(Contrat contrat) {
        if ( contrat == null ) {
            return null;
        }
        Employe employe = contrat.getEmploye();
        if ( employe == null ) {
            return null;
        }
        Long id = employe.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
