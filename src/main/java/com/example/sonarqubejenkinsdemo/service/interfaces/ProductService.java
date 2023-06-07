package com.example.sonarqubejenkinsdemo.service.interfaces;

import com.example.sonarqubejenkinsdemo.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> getProducts();
    ProductDto getProductById(Long idProduct);
    ProductDto updateProduct(Long idProduct, ProductDto newProductDto);
    boolean deleteProduct(Long idProduct);
}