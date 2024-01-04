package com.itgroup.repositories;

import com.itgroup.models.Favorites;
import com.itgroup.models.Product;
import com.itgroup.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FavoritesRepository
        extends JpaRepository<Favorites, Long> {

    @Modifying
    @Query("delete from Favorites f where f.user = :user")
    void deleteAllByUser(User user);

    @Modifying
    @Query("delete from Favorites f where f.user = :user and f.product = :product")
    void deleteProductByUser(User user, Product product);
}
