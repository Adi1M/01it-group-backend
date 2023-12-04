package com.itgroup.controllers;

import com.itgroup.dto.CommentRequest;
import com.itgroup.dto.CommentResponse;
import com.itgroup.dto.ProductResponse;
import com.itgroup.dto.ProductRequest;
import com.itgroup.service.CommentService;
import com.itgroup.service.JwtService;
import com.itgroup.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private CommentService commentService;
    private JwtService jwtService;

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody ProductRequest requestDto) {
        productService.createProduct(requestDto);
        return new ResponseEntity<>("Product successfully created", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> show(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id,
                                         @RequestBody ProductRequest requestDto) {
        productService.updateProduct(id, requestDto);
        return ResponseEntity.ok("Product successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product successfully deleted");
    }

    @GetMapping("/search={some_text}")
    public ResponseEntity<List<ProductResponse>> search(@PathVariable("some_text") String searchText) {
        return ResponseEntity.ok(productService.searchProduct(searchText));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductResponse>> showByCategory(@PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(productService.showByCategory(categoryId));
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<List<ProductResponse>> getByTag(@PathVariable("id") Long tagId) {
        return ResponseEntity.ok(productService.showByTag(tagId));
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<String> createComment(@PathVariable("id") Long id,
                                                @RequestBody CommentRequest commentRequest,
                                                @RequestHeader (name="Authorization") String token) {
        commentService.createComment(id, commentRequest, jwtService.extractUsername(token));
        return new ResponseEntity<>("Comment successfully created", HttpStatus.CREATED);
    }

    @GetMapping("/{id}/comment")
    public ResponseEntity<List<CommentResponse>> getAllCommentsOfProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentService.showAllByProduct(id));
    }

    @PutMapping("/{id}/comment/{comment_id}")
    public ResponseEntity<String> updateComment(@PathVariable("id") Long id,
                                                @PathVariable("comment_id") Long commentId,
                                                @RequestBody CommentRequest commentRequest) {
        commentService.updateComment(id, commentId, commentRequest);
        return ResponseEntity.ok("Comment successfully updated");
    }

    @DeleteMapping("/{id}/comment/{comment_id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long id,
                                @PathVariable("comment_id") Long commentId) {
        commentService.deleteComment(id, commentId);
        return ResponseEntity.ok("Comment successfully deleted");
    }



}
