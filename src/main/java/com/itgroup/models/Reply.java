package com.itgroup.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Timestamp;

@Entity
@Table(name = "Reply")
public class Reply {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "comment_id")
    private int commentId;

    @Column(name = "content")
    @NotEmpty
    private String content;

    @Column(name = "created_by")
    private Timestamp createdBy;
}
