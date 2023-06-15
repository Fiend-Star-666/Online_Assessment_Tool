package com.training.java.security.jpaModels;

import com.training.java.entities.abstrct.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class MyUserDetails implements UserDetails{


    private String emailId;
    private String type="Bearer";
    private String password;
    private boolean active=true;
    private Set<GrantedAuthority> authorities;
    private int accId;


    public MyUserDetails(Account user) {
        this.emailId = user.getEmail();
        this.password=user.getPassword();
        this.accId=user.getId();
        System.out.println("MyUserDetails constructor user: "+user);
        this.authorities = user.getSecurityRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        System.out.println("MyUserDetails constructor authorities: "+authorities);
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
        return true;//active
    }
}
