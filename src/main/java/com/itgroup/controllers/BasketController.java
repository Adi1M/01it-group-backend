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

    //FIXME Solve problems with token in Basket and Product classes for now
    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody BasketRequest basketRequest,
                                      @RequestHeader(name = "Authorization") String token) {
        basketService.addToBasket(basketRequest, jwtService.extractUsername(token.substring(7)));
        return ResponseEntity.ok("Product successfully added");
    }

    @GetMapping("")
    public ResponseEntity<BasketResponse> showAll(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(basketService.showAll(jwtService.extractUsername(token.substring(7))));
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteAll(@RequestHeader(name = "Authorization") String token) {
        basketService.deleteAll(jwtService.extractUsername(token.substring(7)));
        return ResponseEntity.ok("All products successfully deleted");
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<String> delete(@PathVariable("product_id") Long productId,
                                         @RequestHeader(name = "Authorization") String token) {
        basketService.deleteById(productId, jwtService.extractUsername(token.substring(7)));
        return ResponseEntity.ok("Product successfully deleted");
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<String> updateQuantity(@PathVariable("product_id") Long productId,
                                                 @RequestHeader(name = "Authorization") String token,
                                                 @RequestBody ProductInBasketRequest basketRequest) {
        basketService.updateQuantity(productId, jwtService.extractUsername(token.substring(7)), basketRequest);
        return ResponseEntity.ok("Product successfully updated");
    }
}
