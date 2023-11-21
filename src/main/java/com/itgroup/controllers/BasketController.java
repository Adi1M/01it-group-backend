package com.itgroup.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/basket")
public class BasketController {

    @PostMapping("/add")
    public String add() {
        return null;
    }

    @GetMapping("")
    public String showAll() {
        return null;
    }

    @DeleteMapping("/delete")
    public String deleteAll() {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        return null;
    }
}
