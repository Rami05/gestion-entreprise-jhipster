package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Compte_bancaire;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Compte_bancaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Compte_bancaireRepository extends JpaRepository<Compte_bancaire, Long> {

}
