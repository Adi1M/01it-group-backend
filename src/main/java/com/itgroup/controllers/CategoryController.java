package com.itgroup.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @GetMapping("")
    public String showAll() {
        return null;
    }
}
