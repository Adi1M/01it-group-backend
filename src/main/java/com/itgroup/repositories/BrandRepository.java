package com.itgroup.repositories;

import com.itgroup.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrandRepository
        extends JpaRepository<Brand, Long> {

    @Query("select b from Brand b where b.name = :name")
    Brand findByName(String name);
}
