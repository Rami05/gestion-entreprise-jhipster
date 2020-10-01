package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Vehicule entity.
 */
public class VehiculeDTO implements Serializable {

    private Long id;

    private String identifiant;


    private Set<ChauffeurDTO> chauffeurs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public Set<ChauffeurDTO> getChauffeurs() {
        return chauffeurs;
    }

    public void setChauffeurs(Set<ChauffeurDTO> chauffeurs) {
        this.chauffeurs = chauffeurs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VehiculeDTO vehiculeDTO = (VehiculeDTO) o;
        if (vehiculeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehiculeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VehiculeDTO{" +
            "id=" + getId() +
            ", identifiant='" + getIdentifiant() + "'" +
            "}";
    }
}
