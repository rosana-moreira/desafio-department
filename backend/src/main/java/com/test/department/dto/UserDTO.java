package com.test.department.dto;

import com.test.department.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    @NotBlank(message = "O nome é obrigatório")
    private String name;
    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;
    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser anterior à data atual")
    private Instant birthDate;
    private Instant dateRegister;
    @NotBlank(message = "O sexo é obrigatório")
    @Pattern(regexp = "^[MF]$", message = "O sexo deve ser 'M' ou 'F'")
    private String gender;
    private Long companyPositionId;
    private List<ProfileDTO> profile = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String cpf, Instant birthDate, Instant dateRegister, String gender, Long companyPositionId) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.dateRegister = dateRegister;
        this.gender = gender;
        this.companyPositionId = companyPositionId;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        birthDate = entity.getBirthDate();
        dateRegister = entity.getDateRegister();
        gender = entity.getGender();
        companyPositionId = entity.getCompanyPosition().getId();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public Instant getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Instant dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getCompanyPositionId() {
        return companyPositionId;
    }

    public void setCompanyPositionId(Long companyPositionId) {
        this.companyPositionId = companyPositionId;
    }

    public List<ProfileDTO> getProfile() {
        return profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO userDTO)) return false;
        return Objects.equals(getId(), userDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
