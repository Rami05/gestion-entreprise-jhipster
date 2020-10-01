package com.mycompany.myapp.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Chauffeur.
 */
@Entity
@Table(name = "chauffeur")
public class Chauffeur implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "identite")
    private String identite;

    @NotNull
    @Size(min = 8, max = 8)
    @Column(name = "cin", length = 8, nullable = false, unique = true)
    private String cin;

    @ManyToMany(mappedBy = "chauffeurs")
    @JsonIgnore
    private Set<Vehicule> vehicules = new HashSet<>();

    @OneToOne
    @JoinColumn(unique = true)
    private Permis_de_conduit permis_de_conduit;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentite() {
        return identite;
    }

    public Chauffeur identite(String identite) {
        this.identite = identite;
        return this;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public String getCin() {
        return cin;
    }

    public Chauffeur cin(String cin) {
        this.cin = cin;
        return this;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Set<Vehicule> getVehicules() {
        return vehicules;
    }

    public Chauffeur vehicules(Set<Vehicule> vehicules) {
        this.vehicules = vehicules;
        return this;
    }

    public Chauffeur addVehicule(Vehicule vehicule) {
        this.vehicules.add(vehicule);
        vehicule.getChauffeurs().add(this);
        return this;
    }

    public Chauffeur removeVehicule(Vehicule vehicule) {
        this.vehicules.remove(vehicule);
        vehicule.getChauffeurs().remove(this);
        return this;
    }

    public void setVehicules(Set<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

    public Permis_de_conduit getPermis_de_conduit() {
        return permis_de_conduit;
    }

    public Chauffeur permis_de_conduit(Permis_de_conduit permis_de_conduit) {
        this.permis_de_conduit = permis_de_conduit;
        return this;
    }

    public void setPermis_de_conduit(Permis_de_conduit permis_de_conduit) {
        this.permis_de_conduit = permis_de_conduit;
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
        Chauffeur chauffeur = (Chauffeur) o;
        if (chauffeur.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chauffeur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Chauffeur{" +
            "id=" + getId() +
            ", identite='" + getIdentite() + "'" +
            ", cin='" + getCin() + "'" +
            "}";
    }
}
