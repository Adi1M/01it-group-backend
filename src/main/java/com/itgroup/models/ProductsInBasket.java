package com.itgroup.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Products_in_Basket")
@IdClass(ProductsInBasketCompositeKey.class)
public class ProductsInBasket {

    @Id
    @Column(name = "product_id")
    private int productId;

    @Id
    @Column(name = "basket_id")
    private int basketId;

    @Column(name = "quantity")
    private int quantity;
}

