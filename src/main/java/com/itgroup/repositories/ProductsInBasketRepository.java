package com.itgroup.repositories;

import com.itgroup.models.Basket;
import com.itgroup.models.ProductsInBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsInBasketRepository
        extends JpaRepository<ProductsInBasket, Long> {

    @Query("select pb.product.id from ProductsInBasket pb where pb.basket = :basket")
    long[] findIdOfDeletedProducts(Basket basket);

    @Query("select pb.quantity from ProductsInBasket pb where pb.product.id = :productId")
    int findQuantity(long productId);

    @Query("select pb from ProductsInBasket pb where pb.basket = :basket")
    List<ProductsInBasket> findAllByBasket(Basket basket);

    @Query("select pb from ProductsInBasket pb where pb.basket = :basket and pb.product.id = :productId")
    ProductsInBasket findByProductId(Basket basket, Long productId);

    @Modifying
    @Query("delete from ProductsInBasket pb where pb.basket = :basket")
    void deleteAllByBasket(Basket basket);

    @Modifying
    @Query("delete from ProductsInBasket pb where pb.basket = :basket and pb.product.id = :productId")
    void deleteByProductId(Basket basket, Long productId);
}

