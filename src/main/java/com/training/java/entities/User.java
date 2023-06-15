package com.training.java.entities;

import com.training.java.entities.abstrct.Account;
import com.training.java.entities.enums.CodingLanguageEnum;
import com.training.java.entities.enums.StatesOfIndiaEnum;
import com.training.java.entities.enums.QualificationsEnum;
import jakarta.persistence.Entity;
import lombok.*;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends Account {

    @Column(name = "score", nullable = false, columnDefinition = "int default 0")
    private int score;

    @Column(name = "levels_passed", nullable = true, columnDefinition = "int default 0")
    private int levelsPassed;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "coding_language")
    @Column(name = "levels_passed_per_language")
    private Map<CodingLanguageEnum, Integer> languageLevelsMap;

    @Override
    public Boolean resetPassword() {
        //TODO implement resetPassword()
        return null;
    }

}
