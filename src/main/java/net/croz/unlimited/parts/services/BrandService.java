package net.croz.unlimited.parts.services;

import lombok.RequiredArgsConstructor;
import net.croz.unlimited.parts.dto.BrandDTO;
import net.croz.unlimited.parts.model.Brand;
import net.croz.unlimited.parts.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public List<BrandDTO> findAll(){
        List<Brand> brands = brandRepository.findAll();
        List<BrandDTO> brandDTOs = new ArrayList<>();
        brands.forEach(b-> brandDTOs.add(modelMapper.map(b, BrandDTO.class)));

        return brandDTOs;
    }
}
