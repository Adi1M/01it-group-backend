package com.itgroup.service;

import com.itgroup.dto.BasketRequest;
import com.itgroup.dto.BasketResponse;
import com.itgroup.dto.ProductInBasketRequest;
import com.itgroup.mapper.BasketMapper;
import com.itgroup.models.*;
import com.itgroup.repositories.BasketRepository;
import com.itgroup.repositories.ProductRepository;
import com.itgroup.repositories.ProductsInBasketRepository;
import com.itgroup.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BasketService {

    private BasketRepository basketRepository;
    private ProductsInBasketRepository productsInBasketRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    public String addToBasket(BasketRequest request, String email) {
        User user = findUser(email);
        Basket existingBasket = findBasket(email);
        Product product = findProduct(request.getProduct());
        if (request.getQuantity() < product.getQuantity()) {
            if (existingBasket != null) {
                existingBasket.setTotalPrice(BasketMapper
                        .calculateTotalPrice(product.getPrice(),
                                request.getQuantity(), existingBasket.getTotalPrice(), "add"));
                productsInBasketRepository.save(BasketMapper
                        .mapToProductsInBasket(request, existingBasket, product));
            } else {
                Basket newBasket = basketRepository.save(BasketMapper.mapToBasket(user, request, product));
                productsInBasketRepository.save(BasketMapper
                        .mapToProductsInBasket(request, newBasket, product));
            }
            product.setQuantity(product.getQuantity() - request.getQuantity());
            productRepository.save(product);
            return "Successfully recorded";
        } else {
            return "The product has insufficient quantity";
        }
    }

    public BasketResponse showAll(String email) {
        Basket basket = findBasket(email);
        List<ProductsInBasket> products = productsInBasketRepository.findAllByBasket(basket);
        return BasketMapper.mapToBasketResponse(basket, products);
    }

    @Transactional
    public void deleteAll(String email) {
        Basket basket = findBasket(email);
        long[] id = productsInBasketRepository.findIdOfDeletedProducts(basket);
        for (long l : id) {
            Product product = findProduct(l);
            product.setQuantity(productsInBasketRepository.findQuantity(l) + product.getQuantity());
            productRepository.save(product);
        }
        basketRepository.deleteById(basket.getId());
        productsInBasketRepository.deleteAllByBasket(basket);
    }

    @Transactional
    public void deleteById(Long productId, String email) {
        Basket basket = findBasket(email);
        ProductsInBasket productInBasket = productsInBasketRepository.findByProductId(basket, productId);
        Product product = findProduct(productId);
        basket.setTotalPrice(BasketMapper
                .calculateTotalPrice(productInBasket.getProduct().getPrice(),
                        productInBasket.getQuantity(), basket.getTotalPrice(), "subtract"));

        product.setQuantity(product.getQuantity() + productInBasket.getQuantity());
        productRepository.save(product);
        productsInBasketRepository.deleteByProductId(basket, productId);

    }

    public String updateQuantity(Long productId, String email, ProductInBasketRequest request) {
        Basket basket = findBasket(email);
        Product product = findProduct(productId);
        ProductsInBasket existingProduct = productsInBasketRepository.findByProductId(basket, productId);
        if (request.getQuantity() < product.getQuantity()) {
            if (request.getQuantity() > existingProduct.getQuantity()) {
                basket.setTotalPrice(BasketMapper
                        .calculateTotalPrice(existingProduct.getProduct().getPrice(),
                                request.getQuantity() - existingProduct.getQuantity(),
                                basket.getTotalPrice(), "add"));
            } else {
                basket.setTotalPrice(BasketMapper
                        .calculateTotalPrice(existingProduct.getProduct().getPrice(),
                                existingProduct.getQuantity() - request.getQuantity(),
                                basket.getTotalPrice(), "subtract"));
            }
            existingProduct.setQuantity(request.getQuantity());
            productsInBasketRepository.save(existingProduct);
            basketRepository.save(basket);
            return "Successfully updated";
        } else {
            return "The product has insufficient quantity";
        }
    }

    public Basket findBasket(String email) {
        User user = findUser(email);
        return basketRepository.findByUser(user);
    }

    public Product findProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Product not found"));
    }

    public User findUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found"));
    }

}
