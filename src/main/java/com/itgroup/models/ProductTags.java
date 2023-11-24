package com.itgroup.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "Product_tags")
@IdClass(ProductTagsId.class)
public class ProductTags {

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "tag_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tag tag;
}
