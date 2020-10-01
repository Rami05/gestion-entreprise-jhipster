package com.mycompany.myapp.service.dto;
import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import com.mycompany.myapp.domain.enumeration.Etat;

/**
 * A DTO for the COMMANDE_PRODUIT entity.
 */
public class COMMANDE_PRODUITDTO implements Serializable {

    private Long id;

    private LocalDate dateCreation;

    private Instant dateModification;

    private Etat etat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Instant getDateModification() {
        return dateModification;
    }

    public void setDateModification(Instant dateModification) {
        this.dateModification = dateModification;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        COMMANDE_PRODUITDTO cOMMANDE_PRODUITDTO = (COMMANDE_PRODUITDTO) o;
        if (cOMMANDE_PRODUITDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cOMMANDE_PRODUITDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "COMMANDE_PRODUITDTO{" +
            "id=" + getId() +
            ", dateCreation='" + getDateCreation() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
