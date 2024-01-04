package com.itgroup.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@AllArgsConstructor
public class OrderController {

    @PostMapping("")
    public ResponseEntity<String> createOrder() {
        return null;
    }

    @GetMapping("")
    public String showAll() {
        return null;
    }

    @PutMapping("")
    public ResponseEntity<String> updateOrder() {
        return null;
    }
}
