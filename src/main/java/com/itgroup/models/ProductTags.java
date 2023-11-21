package com.itgroup.models;

import jakarta.persistence.*;


@Entity
@Table(name = "Product_tags")
@IdClass(ProductTagsCompositeKey.class)
public class ProductTags {

    @Id
    @Column(name = "product_id")
    private int productId;

    @Id
    @Column(name = "tag_id")
    private int tagId;
}
