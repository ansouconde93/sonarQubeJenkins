package com.example.sonarqubejenkinsdemo.service.impl;

import com.example.sonarqubejenkinsdemo.dao.ProductRepository;
import com.example.sonarqubejenkinsdemo.dto.ProductDto;
import com.example.sonarqubejenkinsdemo.entities.Product;
import com.example.sonarqubejenkinsdemo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return ProductDto.fromEntity(productRepository.save(ProductDto.toEntity(productDto)));
    }
    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductDto::fromEntity)
                .toList();
    }
    @Override
    public ProductDto getProductById(Long idProduct) {
        return productRepository.findById(idProduct)
                .map(ProductDto::fromEntity)
                .orElse(null);
    }
    @Override
    public ProductDto updateProduct(Long idProduct, ProductDto newProductDto) {
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isPresent()){
            if (newProductDto.getName()!=null){
                product.get().setName(newProductDto.getName());
            }
            if (newProductDto.getPrice()!=0){
                product.get().setPrice(newProductDto.getPrice());
            }
            return ProductDto.fromEntity(productRepository.save(product.get()));
        }
        return null;
    }
    @Override
    public boolean deleteProduct(Long idProduct) {
        productRepository.deleteById(idProduct);
        return !productRepository.existsById(idProduct);
    }
}