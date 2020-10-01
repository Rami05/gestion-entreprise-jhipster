package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.Etat;
import java.time.Instant;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(COMMANDE_PRODUIT.class)
public abstract class COMMANDE_PRODUIT_ {

	public static volatile SingularAttribute<COMMANDE_PRODUIT, LocalDate> dateCreation;
	public static volatile SingularAttribute<COMMANDE_PRODUIT, Instant> dateModification;
	public static volatile SingularAttribute<COMMANDE_PRODUIT, Long> id;
	public static volatile SingularAttribute<COMMANDE_PRODUIT, Etat> etat;

}

