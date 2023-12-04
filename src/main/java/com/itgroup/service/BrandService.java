package com.itgroup.service;

import com.itgroup.dto.BrandResponse;
import com.itgroup.dto.BrandRequest;
import com.itgroup.mapper.BrandMapper;
import com.itgroup.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BrandService {

    private BrandRepository brandRepository;

    public void createBrand(BrandRequest requestDto) {
        brandRepository.save(BrandMapper.mapToBrand(requestDto));
    }

    public List<BrandResponse> showAllBrands() {
        return brandRepository.findAll().stream()
                .map(BrandMapper::mapToBrandDto)
                .collect(ArrayList::new, List::add, List::addAll);
    }

    public BrandResponse showByName(@PathVariable String name) {
        return BrandMapper.mapToBrandDto(brandRepository.findByName(name));
    }
}
