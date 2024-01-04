package com.itgroup.mapper;

import com.itgroup.dto.FavoritesResponse;
import com.itgroup.models.Favorites;
import com.itgroup.models.Product;
import com.itgroup.models.User;

import java.util.ArrayList;
import java.util.List;

public class FavoritesMapper {
    private FavoritesMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Favorites mapToFavorites(User user, Product product) {
        return Favorites.builder()
                .product(product)
                .user(user)
                .build();
    }

    public static FavoritesResponse mapToFavoritesResponse(List<Product> favorites) {
        return FavoritesResponse.builder()
                .favorites(favorites
                        .stream().map(ProductMapper::mapToProductDto)
                        .collect(ArrayList::new, List::add, List::addAll))
                .build();
    }
}
