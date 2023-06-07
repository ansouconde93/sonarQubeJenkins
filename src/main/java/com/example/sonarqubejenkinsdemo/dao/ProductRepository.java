package com.example.sonarqubejenkinsdemo.dao;

import com.example.sonarqubejenkinsdemo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
