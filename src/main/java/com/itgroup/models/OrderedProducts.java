package com.itgroup.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Ordered_products")
@IdClass(OrderedProductsCompositeKey.class)
public class OrderedProducts {

    @Id
    @Column(name = "product_id")
    private int productId;

    @Id
    @Column(name = "order_id")
    private int orderId;
}
