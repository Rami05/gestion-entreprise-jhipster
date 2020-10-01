package com.mycompany.myapp.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vehicule.class)
public abstract class Vehicule_ {

	public static volatile SetAttribute<Vehicule, Chauffeur> chauffeurs;
	public static volatile SingularAttribute<Vehicule, String> identifiant;
	public static volatile SingularAttribute<Vehicule, Long> id;

}

