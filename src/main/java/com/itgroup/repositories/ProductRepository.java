package com.itgroup.repositories;

import com.itgroup.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.name ilike %:text% or p.description ilike %:text%")
    List<Product> findByText(String text);

    @Query("select p from Product p where p.category.id = :categoryId")
    List<Product> findByCategory(Long categoryId);

    @Query("select p from Product p, ProductTags pt where pt.tag.id = :tagId and pt.product.id = p.id")
    List<Product> findByTag(Long tagId);

}