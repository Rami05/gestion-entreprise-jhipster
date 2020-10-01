package com.mycompany.myapp.domain;



import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Entreprise.
 */
@Entity
@Table(name = "entreprise")
public class Entreprise implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "raison_social", nullable = false)
    private String raison_social;

    @Column(name = "chiffre_affaire", precision = 10, scale = 2)
    private BigDecimal chiffre_affaire;

    @Column(name = "gerant")
    private String gerant;

    @Column(name = "secteur")
    private String secteur;

    @Column(name = "siege")
    private String siege;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaison_social() {
        return raison_social;
    }

    public Entreprise raison_social(String raison_social) {
        this.raison_social = raison_social;
        return this;
    }

    public void setRaison_social(String raison_social) {
        this.raison_social = raison_social;
    }

    public BigDecimal getChiffre_affaire() {
        return chiffre_affaire;
    }

    public Entreprise chiffre_affaire(BigDecimal chiffre_affaire) {
        this.chiffre_affaire = chiffre_affaire;
        return this;
    }

    public void setChiffre_affaire(BigDecimal chiffre_affaire) {
        this.chiffre_affaire = chiffre_affaire;
    }

    public String getGerant() {
        return gerant;
    }

    public Entreprise gerant(String gerant) {
        this.gerant = gerant;
        return this;
    }

    public void setGerant(String gerant) {
        this.gerant = gerant;
    }

    public String getSecteur() {
        return secteur;
    }

    public Entreprise secteur(String secteur) {
        this.secteur = secteur;
        return this;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getSiege() {
        return siege;
    }

    public Entreprise siege(String siege) {
        this.siege = siege;
        return this;
    }

    public void setSiege(String siege) {
        this.siege = siege;
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
        Entreprise entreprise = (Entreprise) o;
        if (entreprise.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entreprise.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Entreprise{" +
            "id=" + getId() +
            ", raison_social='" + getRaison_social() + "'" +
            ", chiffre_affaire=" + getChiffre_affaire() +
            ", gerant='" + getGerant() + "'" +
            ", secteur='" + getSecteur() + "'" +
            ", siege='" + getSiege() + "'" +
            "}";
    }
}
