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
        return ResponseEntity.ok(productService.searchProduct(searchText));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDto>> showByCategory(@PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(productService.showByCategory(categoryId));
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<List<ProductDto>> getByTag(@PathVariable("id") Long tagId) {
        return ResponseEntity.ok(productService.showByTag(tagId));
    }
}
