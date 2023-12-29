package com.itgroup.dto;


import com.itgroup.models.Product;
import com.itgroup.models.User;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {

    private Long id;
    private Product product;
    private User user;
    private String content;
    private double rating;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
