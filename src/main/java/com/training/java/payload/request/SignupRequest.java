package com.training.java.payload.request;

import com.training.java.entities.enums.QualificationsEnum;
import com.training.java.entities.enums.StatesOfIndiaEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 40)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(min = 10, max = 13)
    private String mobileNumber;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private QualificationsEnum qualification;

    @NotNull
    private Integer yearOfCompletion;

    @NotBlank
    private String city;

    @NotNull
    private StatesOfIndiaEnum state;
}
