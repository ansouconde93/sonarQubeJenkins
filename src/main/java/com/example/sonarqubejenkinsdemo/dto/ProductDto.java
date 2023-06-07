package com.example.sonarqubejenkinsdemo.dto;

import com.example.sonarqubejenkinsdemo.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductDto {
    private Long id;
    private String name;
    private Long price;
    public static Product toEntity(ProductDto productDto){
        return Product.builder()
                .idProduct(null)
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();
    }

    public static ProductDto fromEntity(Product product){
        return ProductDto.builder()
                .id(product.getIdProduct())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
