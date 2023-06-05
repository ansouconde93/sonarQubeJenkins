package com.example.sonarqubejenkins.controller;

import com.example.sonarqubejenkins.dto.ProductDto;
import com.example.sonarqubejenkins.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")

public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto){
       return productService.createProduct(productDto);
    }

    @GetMapping
    public List<ProductDto> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/{idProduct}")
    public ProductDto getProductById(@PathVariable Long idProduct){
        return productService.getProductById(idProduct);
    }
    @PutMapping("/{idProduct}")
    public ProductDto updateProduct(@PathVariable Long idProduct, @RequestBody ProductDto newProductDto){
        return productService.updateProduct(idProduct,newProductDto);
    }

    @DeleteMapping("/{idProduct}")
    public boolean deleteProduct(@PathVariable Long idProduct){
        return productService.deleteProduct(idProduct);
    }
}
