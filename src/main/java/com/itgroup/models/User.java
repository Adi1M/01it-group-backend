package com.itgroup.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;


@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @NotEmpty
    @Max(value = 30, message = "First name should be maximum 30 characters")
    private String first_name;

    @Column(name = "last_name")
    @NotEmpty
    @Max(value = 30, message = "Last name should be maximum 30 characters")
    private String last_name;

    @Column(name = "phone_number")
    @NotEmpty
    private String phone_number;

    @Column(name = "email")
    @NotEmpty
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_day")
    private Date birth_day;

    @Column(name = "role_id")
    private int role_id;

}
