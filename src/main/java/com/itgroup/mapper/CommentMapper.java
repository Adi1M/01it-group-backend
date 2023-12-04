package com.itgroup.mapper;

import com.itgroup.dto.CommentRequest;
import com.itgroup.dto.CommentResponse;
import com.itgroup.models.Comment;
import com.itgroup.models.Product;
import com.itgroup.models.User;

import java.sql.Timestamp;
import java.util.Date;

public class CommentMapper {

    private CommentMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Comment mapToComment(Product product, CommentRequest commentRequest, User user) {
        return Comment.builder()
                .product(product)
                .user(user)
                .content(commentRequest.getContent())
                .rating(commentRequest.getRating())
                .createdAt(new Timestamp(new Date().getTime()))
                .build();
    }

    public static CommentResponse mapToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .product(comment.getProduct())
                .user(comment.getUser())
                .content(comment.getContent())
                .rating(comment.getRating())
                .createdAt(comment.getCreatedAt())
                .build();
    }

}
