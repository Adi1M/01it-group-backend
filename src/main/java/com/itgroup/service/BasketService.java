package com.itgroup.service;

import com.itgroup.dto.BasketRequest;
import com.itgroup.dto.BasketResponse;
import com.itgroup.dto.ProductInBasketRequest;
import com.itgroup.mapper.BasketMapper;
import com.itgroup.models.Basket;
import com.itgroup.models.Product;
import com.itgroup.models.ProductsInBasket;
import com.itgroup.models.User;
import com.itgroup.repositories.BasketRepository;
import com.itgroup.repositories.ProductRepository;
import com.itgroup.repositories.ProductsInBasketRepository;
import com.itgroup.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class BasketService {

    private BasketRepository basketRepository;
    private ProductsInBasketRepository productsInBasketRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    //TODO create check for quantity, does we have enough quantity of product
    public void addToBasket(BasketRequest request, String email) {
        User user = findUser(email);
        Basket existingBasket = findBasket(email);
        Product product = productRepository.findById(request.getProduct()).orElseThrow(() -> new RuntimeException("Product not found"));
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
    }

    public BasketResponse showAll(String email) {
        Basket basket = findBasket(email);
        List<ProductsInBasket> products = productsInBasketRepository.findAllByBasket(basket);
        return BasketMapper.mapToBasketResponse(basket, products);
    }

    //TODO check the other ways to fix problem with Transactional
    @Transactional
    public void deleteAll(String email) {
        Basket basket = findBasket(email);
        basketRepository.deleteById(basket.getId());
        productsInBasketRepository.deleteAllByBasket(basket);
    }

    public void deleteById(Long productId, String email) {
        Basket basket = findBasket(email);
        ProductsInBasket product = productsInBasketRepository.findByProductId(basket, productId);
        basket.setTotalPrice(BasketMapper
                .calculateTotalPrice(product.getProduct().getPrice(),
                        product.getQuantity(), basket.getTotalPrice(), "subtract"));

        productsInBasketRepository.deleteByProductId(basket, productId);
    }

    public void updateQuantity(Long productId, String email, ProductInBasketRequest request) {
        Basket basket = findBasket(email);
        ProductsInBasket existingProduct = productsInBasketRepository.findByProductId(basket, productId);
        existingProduct.setQuantity(request.getQuantity());
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
    }


    private Basket findBasket(String email) {
        User user = findUser(email);
        return basketRepository.findByUser(user);

    }

    private User findUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found"));
    }
}
