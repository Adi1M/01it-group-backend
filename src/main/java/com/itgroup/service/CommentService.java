package com.itgroup.service;

import com.itgroup.dto.CommentRequest;
import com.itgroup.dto.CommentResponse;
import com.itgroup.mapper.CommentMapper;
import com.itgroup.models.Comment;
import com.itgroup.models.Product;
import com.itgroup.models.User;
import com.itgroup.repositories.CommentRepository;
import com.itgroup.repositories.ProductRepository;
import com.itgroup.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public void createComment(Long productId, CommentRequest request, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        commentRepository.save(CommentMapper.mapToComment(findProduct(productId), request, user));
    }

    public List<CommentResponse> showAllByProduct(Long productId) {
        return commentRepository.findAllByProduct(findProduct(productId))
                .stream()
                .map(CommentMapper::mapToCommentResponse)
                .collect(ArrayList::new, List::add, List::addAll);
    }

    public void updateComment(Long productId, Long commentId, CommentRequest commentRequest) {
        Comment existingComment = commentRepository.findByProduct(findProduct(productId), commentId);

        existingComment.setContent(commentRequest.getContent());
        existingComment.setRating(commentRequest.getRating());
        existingComment.setUpdatedAt(new Timestamp(new Date().getTime()));

        commentRepository.save(existingComment);
    }

    public void deleteComment(Long productId, Long commentId) {
        commentRepository.deleteByProduct(findProduct(productId), commentId);
    }

    public Product findProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Product not found"));
    }

}
