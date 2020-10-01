package com.mycompany.myapp.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Permis_de_conduit.class)
public abstract class Permis_de_conduit_ {

	public static volatile SingularAttribute<Permis_de_conduit, byte[]> copie_permis;
	public static volatile SingularAttribute<Permis_de_conduit, Chauffeur> chauffeur;
	public static volatile SingularAttribute<Permis_de_conduit, Long> id;
	public static volatile SingularAttribute<Permis_de_conduit, String> num_permis;
	public static volatile SingularAttribute<Permis_de_conduit, String> copie_permisContentType;

}

