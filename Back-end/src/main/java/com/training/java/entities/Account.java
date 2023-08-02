package com.training.java.entities;

import com.training.java.entities.enums.CodingLanguageEnum;
import com.training.java.entities.enums.QualificationsEnum;
import com.training.java.entities.enums.StatesOfIndiaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @Size(max = 50)
    private String email;

    @Column(name = "password", nullable = false)
    @Size(max = 120)
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
    private Integer yearOfCompletion;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private StatesOfIndiaEnum state;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "score", nullable = false, columnDefinition = "int default 0")
    private Integer score;

    @Column(name = "levels_passed", columnDefinition = "int default 0")
    private Integer levelsPassed;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "coding_language")
    @Column(name = "levels_passed_per_language")
    private Map<CodingLanguageEnum, Integer> languageLevelsMap;


    public Account() {
        this.roles = new HashSet<>();
        this.languageLevelsMap = new HashMap<>();
        this.score = 0;
        this.levelsPassed = 0;
    }

    public Account(String name, String email, String password, String mobileNumber, Date dateOfBirth, QualificationsEnum qualification, int yearOfCompletion, String city, StatesOfIndiaEnum state) {
        this();  // Calls the default constructor to initialize the collections and the int fields
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.dateOfBirth = dateOfBirth;
        this.qualification = qualification;
        this.yearOfCompletion = yearOfCompletion;
        this.city = city;
        this.state = state;
    }


}
