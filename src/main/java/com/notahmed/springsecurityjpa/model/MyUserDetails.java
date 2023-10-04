package com.notahmed.springsecurityjpa.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// hardcoded value
// this UserDetails that will be used in the Spring Security
public class MyUserDetails implements UserDetails {

    // these field will match with my user model
    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;


    // creating constructor
    public MyUserDetails(User user) {

        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();

        // this will get the roles in the shape of string with ","
        // and it will convert to list of type SimpleGrantedAuthority
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

    public MyUserDetails () {

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // hardcoded
//        return Arrays.asList(
//                new SimpleGrantedAuthority("ROLE_USER")
//        );

        return authorities;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return userName;
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

        return active;
    }
}
