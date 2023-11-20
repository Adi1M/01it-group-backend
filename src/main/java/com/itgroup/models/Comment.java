package com.itgroup.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Timestamp;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "content")
    @NotEmpty
    private String content;

    @Column(name = "rating")
    @NotEmpty
    private int rating;

    @Column(name = "created_by")
    private Timestamp createdBy;
}
