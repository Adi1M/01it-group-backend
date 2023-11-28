package com.itgroup.service;

import com.itgroup.dto.ProductDto;
import com.itgroup.dto.ProductRequestDto;
import com.itgroup.mapper.ProductMapper;
import com.itgroup.models.Product;
import com.itgroup.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public void createProduct(ProductRequestDto requestDto) {
        productRepository.save(ProductMapper.mapToProduct(requestDto));
    }

    public ProductDto getProduct(Long id) {
        return ProductMapper.mapToProductDto(findProduct(id));
    }

    @Transactional
    public void updateProduct(Long id, ProductRequestDto requestDto) {
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

    public List<ProductDto> searchProduct(String searchText) {
        List<Product> searchedProducts = productRepository
                .findAllByNameAndDescriptionContainingIgnoreCase(searchText, searchText);
        return searchedProducts.stream()
                .map(ProductMapper::mapToProductDto)
                .collect(ArrayList::new, List::add, List::addAll);
    }

    private Product findProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }


}
