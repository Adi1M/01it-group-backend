package com.itgroup.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Products_in_Basket")
@IdClass(ProductsInBasketCompositeKey.class)
public class ProductsInBasket {

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "basket_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Basket basket;

    @Column(name = "quantity")
    private int quantity;
}

