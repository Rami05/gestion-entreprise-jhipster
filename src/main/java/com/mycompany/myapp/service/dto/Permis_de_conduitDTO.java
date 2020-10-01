package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Permis_de_conduit entity.
 */
public class Permis_de_conduitDTO implements Serializable {

    private Long id;

    @NotNull
    private String num_permis;

    @Lob
    private byte[] copie_permis;

    private String copie_permisContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum_permis() {
        return num_permis;
    }

    public void setNum_permis(String num_permis) {
        this.num_permis = num_permis;
    }

    public byte[] getCopie_permis() {
        return copie_permis;
    }

    public void setCopie_permis(byte[] copie_permis) {
        this.copie_permis = copie_permis;
    }

    public String getCopie_permisContentType() {
        return copie_permisContentType;
    }

    public void setCopie_permisContentType(String copie_permisContentType) {
        this.copie_permisContentType = copie_permisContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Permis_de_conduitDTO permis_de_conduitDTO = (Permis_de_conduitDTO) o;
        if (permis_de_conduitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), permis_de_conduitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Permis_de_conduitDTO{" +
            "id=" + getId() +
            ", num_permis='" + getNum_permis() + "'" +
            ", copie_permis='" + getCopie_permis() + "'" +
            "}";
    }
}
