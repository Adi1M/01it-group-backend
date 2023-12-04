package com.itgroup.repositories;

import com.itgroup.models.Comment;
import com.itgroup.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.product = :product")
    List<Comment> findAllByProduct(Product product);

    @Query("select c from Comment c where c.product = :product and c.id = :commentId")
    Comment findByProduct(Product product, Long commentId);

    @Modifying
    @Query("delete from Comment c where c.product = :product and c.id = :commentId")
    void deleteByProduct(Product product, Long commentId);
}
