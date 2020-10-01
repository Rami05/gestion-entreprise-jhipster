package com.mycompany.myapp.domain;



import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Compte_bancaire.
 */
@Entity
@Table(name = "compte_bancaire")
public class Compte_bancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @DecimalMin(value = "1000000000")
    @DecimalMax(value = "9999999999")
    @Column(name = "rib", precision = 10, scale = 2)
    private BigDecimal rib;

    @Column(name = "agence")
    private String agence;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRib() {
        return rib;
    }

    public Compte_bancaire rib(BigDecimal rib) {
        this.rib = rib;
        return this;
    }

    public void setRib(BigDecimal rib) {
        this.rib = rib;
    }

    public String getAgence() {
        return agence;
    }

    public Compte_bancaire agence(String agence) {
        this.agence = agence;
        return this;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Compte_bancaire compte_bancaire = (Compte_bancaire) o;
        if (compte_bancaire.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), compte_bancaire.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Compte_bancaire{" +
            "id=" + getId() +
            ", rib=" + getRib() +
            ", agence='" + getAgence() + "'" +
            "}";
    }
}
