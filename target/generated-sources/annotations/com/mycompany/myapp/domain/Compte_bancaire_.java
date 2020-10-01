package com.mycompany.myapp.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Compte_bancaire.class)
public abstract class Compte_bancaire_ {

	public static volatile SingularAttribute<Compte_bancaire, String> agence;
	public static volatile SingularAttribute<Compte_bancaire, BigDecimal> rib;
	public static volatile SingularAttribute<Compte_bancaire, Long> id;

}

