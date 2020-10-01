package com.mycompany.myapp.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Chauffeur.class)
public abstract class Chauffeur_ {

	public static volatile SingularAttribute<Chauffeur, String> identite;
	public static volatile SetAttribute<Chauffeur, Vehicule> vehicules;
	public static volatile SingularAttribute<Chauffeur, String> cin;
	public static volatile SingularAttribute<Chauffeur, Long> id;
	public static volatile SingularAttribute<Chauffeur, Permis_de_conduit> permis_de_conduit;

}

