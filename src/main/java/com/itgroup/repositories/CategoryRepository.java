package com.itgroup.repositories;

import com.itgroup.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c.parent FROM Category c WHERE c.parent = null ")
    List<Category> findAllByParentIsNull();

}
