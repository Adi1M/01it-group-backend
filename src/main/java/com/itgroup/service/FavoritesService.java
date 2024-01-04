package com.itgroup.service;

import com.itgroup.dto.FavoritesRequest;
import com.itgroup.mapper.FavoritesMapper;
import com.itgroup.models.Product;
import com.itgroup.models.User;
import com.itgroup.repositories.FavoritesRepository;
import com.itgroup.repositories.ProductRepository;
import com.itgroup.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoritesService {

    private FavoritesRepository favoritesRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    public void addToFavorites(FavoritesRequest request, String email) {
        User user = findUser(email);
        Product product = findProduct(request.getProduct());
        favoritesRepository.save(FavoritesMapper.mapToFavorites(user, product));
    }

    @Transactional
    public void deleteAll(String email) {
        User user = findUser(email);
        favoritesRepository.deleteAllByUser(user);
    }

    @Transactional
    public void deleteProduct(Long productId, String email) {
        User user = findUser(email);
        Product product = findProduct(productId);
        favoritesRepository.deleteProductByUser(user, product);
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
