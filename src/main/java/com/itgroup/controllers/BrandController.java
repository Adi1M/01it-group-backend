package com.itgroup.controllers;

import com.itgroup.dto.BrandResponse;
import com.itgroup.dto.BrandRequest;
import com.itgroup.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/brands")
@AllArgsConstructor
public class BrandController {

    private BrandService brandService;

    @GetMapping("")
    public ResponseEntity<List<BrandResponse>> showAll() {
        return ResponseEntity.ok(brandService.showAllBrands());
    }

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody BrandRequest requestDto) {
        brandService.createBrand(requestDto);
        return new ResponseEntity<>("Brand successfully created", HttpStatus.CREATED);
    }

    @GetMapping("/{brand_name}")
    public ResponseEntity<BrandResponse> getByName(@PathVariable("brand_name") String name) {
        return ResponseEntity.ok(brandService.showByName(name));
    }
}
