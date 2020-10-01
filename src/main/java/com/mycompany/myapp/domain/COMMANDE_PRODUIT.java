package com.mycompany.myapp.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

import com.mycompany.myapp.domain.enumeration.Etat;

/**
 * A COMMANDE_PRODUIT.
 */
@Entity
@Table(name = "commande_produit")
public class COMMANDE_PRODUIT implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "date_modification")
    private Instant dateModification;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private Etat etat;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public COMMANDE_PRODUIT dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateModification() {
        return dateModification;
    }

    public COMMANDE_PRODUIT dateModification(Instant dateModification) {
        this.dateModification = dateModification;
        return this;
    }

    public void setDateModification(Instant dateModification) {
        this.dateModification = dateModification;
    }

    public Etat getEtat() {
        return etat;
    }

    public COMMANDE_PRODUIT etat(Etat etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
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
        COMMANDE_PRODUIT cOMMANDE_PRODUIT = (COMMANDE_PRODUIT) o;
        if (cOMMANDE_PRODUIT.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cOMMANDE_PRODUIT.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "COMMANDE_PRODUIT{" +
            "id=" + getId() +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
