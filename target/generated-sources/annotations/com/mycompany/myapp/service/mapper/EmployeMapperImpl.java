package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Employe;
import com.mycompany.myapp.service.dto.EmployeDTO;
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
public class EmployeMapperImpl implements EmployeMapper {

    @Override
    public Employe toEntity(EmployeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Employe employe = new Employe();

        employe.setId( dto.getId() );
        employe.setNom( dto.getNom() );
        employe.setPrenom( dto.getPrenom() );

        return employe;
    }

    @Override
    public EmployeDTO toDto(Employe entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeDTO employeDTO = new EmployeDTO();

        employeDTO.setId( entity.getId() );
        employeDTO.setNom( entity.getNom() );
        employeDTO.setPrenom( entity.getPrenom() );

        return employeDTO;
    }

    @Override
    public List<Employe> toEntity(List<EmployeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Employe> list = new ArrayList<Employe>( dtoList.size() );
        for ( EmployeDTO employeDTO : dtoList ) {
            list.add( toEntity( employeDTO ) );
        }

        return list;
    }

    @Override
    public List<EmployeDTO> toDto(List<Employe> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EmployeDTO> list = new ArrayList<EmployeDTO>( entityList.size() );
        for ( Employe employe : entityList ) {
            list.add( toDto( employe ) );
        }

        return list;
    }
}
