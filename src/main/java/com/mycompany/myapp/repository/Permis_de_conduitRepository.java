package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Permis_de_conduit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Permis_de_conduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Permis_de_conduitRepository extends JpaRepository<Permis_de_conduit, Long> {

}
