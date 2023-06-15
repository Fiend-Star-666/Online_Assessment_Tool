package com.training.java.entities.abstrct;

import com.training.java.entities.enums.QualificationsEnum;
import com.training.java.entities.enums.StatesOfIndiaEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "account")
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "mobileNumber", nullable = false, unique = true)
    private String mobileNumber;

    @Column(name = "dateOfBirth", nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "qualification", nullable = false)
    private QualificationsEnum qualification;

    @Column(name = "yearOfCompletion", nullable = false)
    private int yearOfCompletion;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private StatesOfIndiaEnum state;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "account_security_roles", joinColumns = @JoinColumn(name = "account_id"))
    @Column(name = "security_role")
    private Set<String> securityRoles;

    public abstract Boolean resetPassword();

}
