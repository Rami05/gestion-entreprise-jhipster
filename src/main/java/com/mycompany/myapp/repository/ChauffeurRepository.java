package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Chauffeur;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Chauffeur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChauffeurRepository extends JpaRepository<Chauffeur, Long> {

}
