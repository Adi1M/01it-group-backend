package com.itgroup.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "products_in_Basket")
@IdClass(ProductsInBasketId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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

