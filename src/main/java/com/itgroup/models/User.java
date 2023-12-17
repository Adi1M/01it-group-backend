package com.itgroup.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;



@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty
//    @Max(value = 30, message = "First name should be maximum 30 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
//    @Max(value = 30, message = "Last name should be maximum 30 characters")
    private String lastName;

    @Column(name = "phone_number", unique = true)
    @NotEmpty
    private String phoneNumber;

    @Column(name = "email", unique = true)
    @NotEmpty
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name="role_id")
    private int roleId;

    @Column(name = "password")
    private String password;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "avatar")
    private String avatar;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role;
        if (roleId == 1) {
            role = "ADMIN";
        }else {
            role = "USER";
        }
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}
