package com.itgroup.repositories;

import com.itgroup.models.Basket;
import com.itgroup.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BasketRepository
        extends JpaRepository<Basket, Long> {
    @Query("select b from Basket b where b.user = :user")
    Basket findByUser(User user);
}
