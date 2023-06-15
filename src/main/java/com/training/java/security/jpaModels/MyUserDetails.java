package com.training.java.security.jpaModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.java.entities.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class MyUserDetails implements UserDetails {

    private static final int serialVersionUID = 1;

    private int accId;

    private String emailId;
    private String type = "Bearer";

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;


    public MyUserDetails(int id, String email, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.accId = id;
        this.emailId = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static MyUserDetails build(Account user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new MyUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    @Override
    public String getUsername() {
        return emailId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MyUserDetails user = (MyUserDetails) o;
        return Objects.equals(accId, user.accId);
    }
}
