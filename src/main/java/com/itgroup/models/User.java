package com.itgroup.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty
    @Max(value = 30, message = "First name should be maximum 30 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    @Max(value = 30, message = "Last name should be maximum 30 characters")
    private String lastName;

    @Column(name = "phone_number")
    @NotEmpty
    private String phoneNumber;

    @Column(name = "email")
    @NotEmpty
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Role role;

}
