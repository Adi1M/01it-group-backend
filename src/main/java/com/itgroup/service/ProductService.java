package com.itgroup.service;

import com.itgroup.dto.ProductResponse;
import com.itgroup.dto.ProductRequest;
import com.itgroup.mapper.ProductMapper;
import com.itgroup.models.Product;
import com.itgroup.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public void createProduct(ProductRequest requestDto) {
        productRepository.save(ProductMapper.mapToProduct(requestDto));
    }

    public ProductResponse getProduct(Long id) {
        return ProductMapper.mapToProductDto(findProduct(id));
    }

    public void updateProduct(Long id, ProductRequest requestDto) {
        Product existingProduct = findProduct(id);
        existingProduct.setName(requestDto.getName());
        existingProduct.setPrice(requestDto.getPrice());
        existingProduct.setDescription(requestDto.getDescription());
        existingProduct.setCategory(requestDto.getCategory());
        existingProduct.setBrand(requestDto.getBrand());
        existingProduct.setImgUrl(requestDto.getImgUrl());
        existingProduct.setQuantity(requestDto.getQuantity());

        productRepository.save(existingProduct);
    }


    public void deleteProduct(Long id) {
        productRepository.delete(findProduct(id));
    }

    public List<ProductResponse> searchProduct(String searchText) {
        return productRepository.findByText(searchText)
                .stream()
                .map(ProductMapper::mapToProductDto)
                .collect(ArrayList::new, List::add, List::addAll);
    }

    public List<ProductResponse> showByCategory(Long categoryId) {
        return productRepository.findByCategory(categoryId)
                .stream()
                .map(ProductMapper::mapToProductDto)
                .collect(ArrayList::new, List::add, List::addAll);
    }

    public List<ProductResponse> showByTag(Long tagId) {
        return productRepository.findByTag(tagId)
                .stream()
                .map(ProductMapper::mapToProductDto)
                .collect(ArrayList::new, List::add, List::addAll);
    }

    private Product findProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
