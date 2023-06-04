package com.test.department.dto;

import com.test.department.entities.CompanyPosition;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;

public class CompanyPositionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    public CompanyPositionDTO() {
    }

    public CompanyPositionDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyPositionDTO(CompanyPosition entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyPositionDTO that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
