package com.mycompany.myapp.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Vehicule.
 */
@Entity
@Table(name = "vehicule")
public class Vehicule implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "identifiant")
    private String identifiant;

    @ManyToMany
    @JoinTable(name = "vehicule_chauffeur",
               joinColumns = @JoinColumn(name = "vehicule_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "chauffeur_id", referencedColumnName = "id"))
    private Set<Chauffeur> chauffeurs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public Vehicule identifiant(String identifiant) {
        this.identifiant = identifiant;
        return this;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public Set<Chauffeur> getChauffeurs() {
        return chauffeurs;
    }

    public Vehicule chauffeurs(Set<Chauffeur> chauffeurs) {
        this.chauffeurs = chauffeurs;
        return this;
    }

    public Vehicule addChauffeur(Chauffeur chauffeur) {
        this.chauffeurs.add(chauffeur);
        chauffeur.getVehicules().add(this);
        return this;
    }

    public Vehicule removeChauffeur(Chauffeur chauffeur) {
        this.chauffeurs.remove(chauffeur);
        chauffeur.getVehicules().remove(this);
        return this;
    }

    public void setChauffeurs(Set<Chauffeur> chauffeurs) {
        this.chauffeurs = chauffeurs;
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
        Vehicule vehicule = (Vehicule) o;
        if (vehicule.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicule.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vehicule{" +
            "id=" + getId() +
            ", identifiant='" + getIdentifiant() + "'" +
            "}";
    }
}
