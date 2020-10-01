package com.mycompany.myapp.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Entreprise.class)
public abstract class Entreprise_ {

	public static volatile SingularAttribute<Entreprise, String> secteur;
	public static volatile SingularAttribute<Entreprise, String> gerant;
	public static volatile SingularAttribute<Entreprise, BigDecimal> chiffre_affaire;
	public static volatile SingularAttribute<Entreprise, String> siege;
	public static volatile SingularAttribute<Entreprise, Long> id;
	public static volatile SingularAttribute<Entreprise, String> raison_social;

}

