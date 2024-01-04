package com.itgroup.controllers;

import com.itgroup.dto.FavoritesRequest;
import com.itgroup.service.FavoritesService;
import com.itgroup.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/favorites")
@AllArgsConstructor
public class FavoritesController {
    private FavoritesService favoritesService;
    private JwtService jwtService;

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody FavoritesRequest request,
            @RequestHeader(name = "Authorization") String token) {
        favoritesService.addToFavorites(request, jwtService.extractUsername(token));
        return ResponseEntity.ok("Product successfully added into favorites");
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteAll(@RequestHeader(name = "Authorization") String token) {
        favoritesService.deleteAll(jwtService.extractUsername(token));
        return ResponseEntity.ok("All products successfully deleted from favorites");
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<String> delete(@PathVariable("product_id") Long productId,
                                         @RequestHeader(name = "Authorization") String token) {
        favoritesService.deleteProduct(productId, jwtService.extractUsername(token));
        return ResponseEntity.ok("Product successfully deleted from favorites");
    }
}



