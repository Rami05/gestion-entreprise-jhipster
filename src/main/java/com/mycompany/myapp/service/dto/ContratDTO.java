package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Contrat entity.
 */
public class ContratDTO implements Serializable {

    private Long id;

    private String reference;


    private Long employeId;

    private String employeNom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public String getEmployeNom() {
        return employeNom;
    }

    public void setEmployeNom(String employeNom) {
        this.employeNom = employeNom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContratDTO contratDTO = (ContratDTO) o;
        if (contratDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contratDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContratDTO{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", employe=" + getEmployeId() +
            ", employe='" + getEmployeNom() + "'" +
            "}";
    }
}
