package com.test.department.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_user")
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "name")),
        @AttributeOverride(name = "cpf", column = @Column(name = "cpf", unique = true, nullable = false)),
        @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date")),
        @AttributeOverride(name = "gender", column = @Column(name = "gender"))
})
public class User extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_register")
    private Instant dateRegister;

    @ManyToOne
    @JoinColumn(name = "company_position_id")
    private CompanyPosition companyPosition;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_profile",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id"))
    private List<Profile> profiles = new ArrayList<>();

    public User() {
    }

    public User(String name, String cpf, Instant birthDate, String gender, Long id, Instant dateRegister, CompanyPosition companyPosition) {
        super(name, cpf, birthDate, gender);
        this.id = id;
        this.dateRegister = dateRegister;
        this.companyPosition = companyPosition;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Instant dateRegister) {
        this.dateRegister = dateRegister;
    }

    public CompanyPosition getCompanyPosition() {
        return companyPosition;
    }

    public void setCompanyPosition(CompanyPosition companyPosition) {
        this.companyPosition = companyPosition;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
