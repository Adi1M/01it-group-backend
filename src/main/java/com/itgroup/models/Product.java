package com.itgroup.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;


@Entity
@Table(name = "Product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "price")
    @NotEmpty
    private BigDecimal price;

    @Column(name = "description")
    @NotEmpty
    private String description;

    @Column(name = "rating_total")
    private BigDecimal ratingTotal;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "brand_id")
    private int brandId;

    @Column(name = "img_url")
    @NotEmpty
    private String imgUrl;

    @Column(name = "quantity")
    @NotEmpty
    private int quantity;
}
