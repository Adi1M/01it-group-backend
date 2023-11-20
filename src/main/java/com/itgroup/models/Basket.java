package com.itgroup.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Basket")
public class Basket {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "total_price")
    private BigDecimal totalPrice;
}