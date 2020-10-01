package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.COMMANDE_PRODUIT;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the COMMANDE_PRODUIT entity.
 */
@SuppressWarnings("unused")
@Repository
public interface COMMANDE_PRODUITRepository extends JpaRepository<COMMANDE_PRODUIT, Long> {

}
