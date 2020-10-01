package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Chauffeur entity.
 */
public class ChauffeurDTO implements Serializable {

    private Long id;

    private String identite;

    @NotNull
    @Size(min = 8, max = 8)
    private String cin;


    private Long permis_de_conduitId;

    private String permis_de_conduitNum_permis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentite() {
        return identite;
    }

    public void setIdentite(String identite) {
        this.identite = identite;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Long getPermis_de_conduitId() {
        return permis_de_conduitId;
    }

    public void setPermis_de_conduitId(Long permis_de_conduitId) {
        this.permis_de_conduitId = permis_de_conduitId;
    }

    public String getPermis_de_conduitNum_permis() {
        return permis_de_conduitNum_permis;
    }

    public void setPermis_de_conduitNum_permis(String permis_de_conduitNum_permis) {
        this.permis_de_conduitNum_permis = permis_de_conduitNum_permis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChauffeurDTO chauffeurDTO = (ChauffeurDTO) o;
        if (chauffeurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chauffeurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChauffeurDTO{" +
            "id=" + getId() +
            ", identite='" + getIdentite() + "'" +
            ", cin='" + getCin() + "'" +
            ", permis_de_conduit=" + getPermis_de_conduitId() +
            ", permis_de_conduit='" + getPermis_de_conduitNum_permis() + "'" +
            "}";
    }
}
