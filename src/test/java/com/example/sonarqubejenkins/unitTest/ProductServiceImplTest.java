package com.example.sonarqubejenkins.unitTest;

import com.example.sonarqubejenkins.dao.ProductRepository;
import com.example.sonarqubejenkins.dto.ProductDto;
import com.example.sonarqubejenkins.entities.Product;
import com.example.sonarqubejenkins.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;
    private Product product;

    @BeforeEach
    void initVariable(){
        product = Product.builder()
                .idProduct(1L)
                .name("tomate")
                .price(70L)
                .build();
    }

    @Test
    void createProduct() {
        ProductDto productDto1 = ProductDto.builder()
                .name("tomate")
                .price(70L)
                .build();
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDto productDto2 = productServiceImpl.createProduct(productDto1);

        assertNotNull(productDto2);
        assertNotNull(productDto2.getId());
        assertEquals(product.getIdProduct(), productDto2.getId());
    }

    @Test
    void getProducts() {

        List<Product> products = Arrays.asList(
                product,
                Product.builder()
                        .idProduct(2L)
                        .name("tomate")
                        .price(70L)
                        .build()
        );
        when(productRepository.findAll()).thenReturn(products);

        List<ProductDto> productDtos = productServiceImpl.getProducts();

        assertNotNull(productDtos);
        assertEquals(2, productDtos.size());
    }

    @Test
    void getProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));
        ProductDto productDto = productServiceImpl.getProductById(1L);
        assertNotNull(productDto);
    }

    @Test
    void updateProduct() {
        Product product2 = Product.builder()
                .idProduct(1L)
                .name("Huile")
                .price(90L)
                .build();
        ProductDto producDto1 = ProductDto.builder()
                .name("Huile")
                .price(90L)
                .build();
        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(product));
        when(productRepository.save(product2)).thenReturn(product2);

        ProductDto productDto2 = productServiceImpl.updateProduct(1L, producDto1);

        assertNotNull(productDto2);
        assertEquals(product2.getIdProduct(), productDto2.getId());
        assertEquals(product2.getPrice(), productDto2.getPrice());
        assertEquals(product2.getName(), productDto2.getName());
    }

    @Test
    void deleteProduct() {

        doNothing().when(productRepository).deleteById(1L);
        when(productRepository.existsById(1L)).thenReturn(false);

        boolean deleted = productServiceImpl.deleteProduct(1L);
        assertTrue(deleted);
    }
}