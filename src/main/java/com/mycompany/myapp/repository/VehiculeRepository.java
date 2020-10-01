package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Vehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Vehicule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    @Query(value = "select distinct vehicule from Vehicule vehicule left join fetch vehicule.chauffeurs",
        countQuery = "select count(distinct vehicule) from Vehicule vehicule")
    Page<Vehicule> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct vehicule from Vehicule vehicule left join fetch vehicule.chauffeurs")
    List<Vehicule> findAllWithEagerRelationships();

    @Query("select vehicule from Vehicule vehicule left join fetch vehicule.chauffeurs where vehicule.id =:id")
    Optional<Vehicule> findOneWithEagerRelationships(@Param("id") Long id);

}
