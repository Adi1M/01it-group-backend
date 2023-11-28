package com.itgroup.repositories;

import com.itgroup.models.Product;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    List<Product> findAllByNameAndDescriptionContainingIgnoreCase(@NotEmpty String name, @NotEmpty String description);

}