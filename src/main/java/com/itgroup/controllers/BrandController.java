package com.itgroup.controllers;

import com.itgroup.dto.BrandDto;
import com.itgroup.dto.BrandRequestDto;
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
    public ResponseEntity<List<BrandDto>> showAll() {
        return ResponseEntity.ok(brandService.showAllBrands());
    }

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody BrandRequestDto requestDto) {
        brandService.createBrand(requestDto);
        return new ResponseEntity<>("Brand successfully created", HttpStatus.CREATED);
    }

    @GetMapping("/{brand_name}")
    public ResponseEntity<BrandDto> getByName(@PathVariable("brand_name") String name) {
        return ResponseEntity.ok(brandService.showByName(name));
    }
}
