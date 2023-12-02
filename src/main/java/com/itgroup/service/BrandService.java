package com.itgroup.service;

import com.itgroup.dto.BrandDto;
import com.itgroup.dto.BrandRequestDto;
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

    public void createBrand(BrandRequestDto requestDto) {
        brandRepository.save(BrandMapper.mapToBrand(requestDto));
    }

    public List<BrandDto> showAllBrands() {
        return brandRepository.findAll().stream()
                .map(BrandMapper::mapToBrandDto)
                .collect(ArrayList::new, List::add, List::addAll);
    }

    public BrandDto showByName(@PathVariable String name) {
        return BrandMapper.mapToBrandDto(brandRepository.findByName(name));
    }
}
