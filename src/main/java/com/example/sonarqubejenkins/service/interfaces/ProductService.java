package com.example.sonarqubejenkins.service.interfaces;

import com.example.sonarqubejenkins.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> getProducts();
    ProductDto getProductById(Long idProduct);
    ProductDto updateProduct(Long idProduct, ProductDto newProductDto);
    boolean deleteProduct(Long idProduct);
}