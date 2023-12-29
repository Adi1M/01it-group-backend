package com.itgroup.mapper;

import com.itgroup.dto.BasketRequest;
import com.itgroup.dto.BasketResponse;
import com.itgroup.dto.ProductsInBasketResponse;
import com.itgroup.models.Basket;
import com.itgroup.models.Product;
import com.itgroup.models.ProductsInBasket;
import com.itgroup.models.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasketMapper {

    private BasketMapper() {
        throw new IllegalStateException("Utility class");
    }


    public static Basket mapToBasket(User user, BasketRequest request, Product product) {
        return Basket.builder()
                .user(user)
                .totalPrice(calculateTotalPrice(product.getPrice(),
                        request.getQuantity(), new BigDecimal("0"), "add"))
                .build();
    }

    public static ProductsInBasket mapToProductsInBasket(BasketRequest request, Basket basket, Product product) {
        return ProductsInBasket.builder()
                .product(product)
                .basket(basket)
                .quantity(request.getQuantity())
                .build();
    }

    public static BasketResponse mapToBasketResponse(Basket basket, List<ProductsInBasket> productsInBaskets) {
        return BasketResponse.builder()
                .id(basket.getId())
                .user(basket.getUser())
                .products(productsInBaskets
                        .stream().map(BasketMapper::mapToProductsInBasketResponse)
                        .collect(ArrayList::new, List::add, List::addAll))
                .totalPrice(basket.getTotalPrice())
                .build();
    }

    public static ProductsInBasketResponse mapToProductsInBasketResponse(ProductsInBasket products) {
        return ProductsInBasketResponse.builder()
                .product(products.getProduct())
                .quantity(products.getQuantity())
                .totalPrice(calculateTotalPrice(products.getProduct().getPrice(),
                        products.getQuantity(), new BigDecimal("0"), "add"))
                .build();
    }


    public static BigDecimal calculateTotalPrice(BigDecimal productPrice, int quantity, BigDecimal oldTotalPrice, String operation) {
        BigDecimal priceOfProducts = productPrice.multiply(BigDecimal.valueOf(quantity));
        if (operation.equals("add")) {
            if (oldTotalPrice.compareTo(BigDecimal.ZERO) > 0)
                return oldTotalPrice.add(priceOfProducts);
            return priceOfProducts;
        } else if (operation.equals("subtract")) {
            return oldTotalPrice.subtract(priceOfProducts);
        }

        return new BigDecimal(0);
    }
}
