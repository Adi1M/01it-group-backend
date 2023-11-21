package com.itgroup.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/brands")
public class BrandController {

    @GetMapping("")
    public String showAll() {
        return null;
    }

    @PostMapping("")
    public String add() {
        return null;
    }

    @GetMapping("/{brand_name}")
    public String getById(@PathVariable("brand_name") String name) {
        return null;
    }
}
