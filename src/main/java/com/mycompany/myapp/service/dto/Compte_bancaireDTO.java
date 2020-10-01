package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Compte_bancaire entity.
 */
public class Compte_bancaireDTO implements Serializable {

    private Long id;

    @DecimalMin(value = "1000000000")
    @DecimalMax(value = "9999999999")
    private BigDecimal rib;

    private String agence;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRib() {
        return rib;
    }

    public void setRib(BigDecimal rib) {
        this.rib = rib;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Compte_bancaireDTO compte_bancaireDTO = (Compte_bancaireDTO) o;
        if (compte_bancaireDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), compte_bancaireDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Compte_bancaireDTO{" +
            "id=" + getId() +
            ", rib=" + getRib() +
            ", agence='" + getAgence() + "'" +
            "}";
    }
}
