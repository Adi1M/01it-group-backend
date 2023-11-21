package com.itgroup.controllers;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/products")
public class ProductController {

    @PostMapping("")
    public String create() {
        return null;
    }

    @GetMapping("")
    public String showAll() {
        return null;
    }

    @GetMapping("/search={some_text}")
    public String search(@PathVariable("some_text") String text) {
        return null;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id) {
        return null;
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return null;
    }

    @GetMapping("/{category_id}")
    public String showByCategory(@PathVariable("category_id") int category_id) {
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
