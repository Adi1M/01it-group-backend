package com.itgroup.controllers;

import com.itgroup.dto.ProductDto;
import com.itgroup.dto.ProductRequestDto;
import com.itgroup.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody ProductRequestDto requestDto) {
        productService.createProduct(requestDto);
        return new ResponseEntity<>("Product successfully created", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> show(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody ProductRequestDto requestDto) {
        productService.updateProduct(id, requestDto);
        return ResponseEntity.ok("Product successfully updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product successfully deleted");
    }

    @GetMapping("/search={some_text}")
    public ResponseEntity<List<ProductDto>> search(@PathVariable("some_text") String searchText) {
        List<ProductDto> products = productService.searchProduct(searchText);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{category_id}")
    public String showByCategory(@PathVariable("category_id") Long categoryId) {
        return null;
    }

    @GetMapping("/new")
    public String getNewProducts() {
        return null;
    }

    @GetMapping("/popular")
    public String getPopularProducts() {
        return null;
    }

    @GetMapping("/recommendations")
    public String getRecommendedProducts() {
        return null;
    }
}
