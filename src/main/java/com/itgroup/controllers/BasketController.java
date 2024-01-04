package com.itgroup.controllers;

import com.itgroup.dto.BasketRequest;
import com.itgroup.dto.BasketResponse;
import com.itgroup.dto.ProductInBasketRequest;
import com.itgroup.service.BasketService;
import com.itgroup.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/basket")
@AllArgsConstructor
public class BasketController {

    private BasketService basketService;
    private JwtService jwtService;

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody BasketRequest basketRequest,
                                      @RequestHeader(name = "Authorization") String token) {
        if (basketService.addToBasket(basketRequest, jwtService.extractUsername(token)).startsWith("Successfully")){
            return ResponseEntity.ok("Product successfully added into basket");
        }
        return ResponseEntity.badRequest().body("The product has insufficient quantity");
    }

    @GetMapping("")
    public ResponseEntity<BasketResponse> showAll(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(basketService.showAll(jwtService.extractUsername(token)));
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteAll(@RequestHeader(name = "Authorization") String token) {
        basketService.deleteAll(jwtService.extractUsername(token));
        return ResponseEntity.ok("All products successfully deleted from basket");
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<String> delete(@PathVariable("product_id") Long productId,
                                         @RequestHeader(name = "Authorization") String token) {
        basketService.deleteById(productId, jwtService.extractUsername(token));
        return ResponseEntity.ok("Product successfully deleted from basket");
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<String> updateQuantity(@PathVariable("product_id") Long productId,
                                                 @RequestHeader(name = "Authorization") String token,
                                                 @RequestBody ProductInBasketRequest basketRequest) {
        if (basketService.updateQuantity(productId, jwtService.extractUsername(token), basketRequest).startsWith("Successfully")){
            return ResponseEntity.ok("Product in basket successfully updated");
        }
        return ResponseEntity.badRequest().body("The product has insufficient quantity");
    }
}
