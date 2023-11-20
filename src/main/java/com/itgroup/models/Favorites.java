package com.itgroup.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Favorites")
@IdClass(FavoritesCompositeKey.class)
public class Favorites {

    @Id
    @Column(name = "product_id")
    private int productId;

    @Id
    @Column(name = "user_id")
    private int userId;
}
