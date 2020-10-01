package com.mycompany.myapp.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Permis_de_conduit.
 */
@Entity
@Table(name = "permis_de_conduit")
public class Permis_de_conduit implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "num_permis", nullable = false)
    private String num_permis;

    @Lob
    @Column(name = "copie_permis")
    private byte[] copie_permis;

    @Column(name = "copie_permis_content_type")
    private String copie_permisContentType;

    @OneToOne(mappedBy = "permis_de_conduit")
    @JsonIgnore
    private Chauffeur chauffeur;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum_permis() {
        return num_permis;
    }

    public Permis_de_conduit num_permis(String num_permis) {
        this.num_permis = num_permis;
        return this;
    }

    public void setNum_permis(String num_permis) {
        this.num_permis = num_permis;
    }

    public byte[] getCopie_permis() {
        return copie_permis;
    }

    public Permis_de_conduit copie_permis(byte[] copie_permis) {
        this.copie_permis = copie_permis;
        return this;
    }

    public void setCopie_permis(byte[] copie_permis) {
        this.copie_permis = copie_permis;
    }

    public String getCopie_permisContentType() {
        return copie_permisContentType;
    }

    public Permis_de_conduit copie_permisContentType(String copie_permisContentType) {
        this.copie_permisContentType = copie_permisContentType;
        return this;
    }

    public void setCopie_permisContentType(String copie_permisContentType) {
        this.copie_permisContentType = copie_permisContentType;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public Permis_de_conduit chauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
        return this;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
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
        Permis_de_conduit permis_de_conduit = (Permis_de_conduit) o;
        if (permis_de_conduit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), permis_de_conduit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Permis_de_conduit{" +
            "id=" + getId() +
            ", num_permis='" + getNum_permis() + "'" +
            ", copie_permis='" + getCopie_permis() + "'" +
            ", copie_permisContentType='" + getCopie_permisContentType() + "'" +
            "}";
    }
}
