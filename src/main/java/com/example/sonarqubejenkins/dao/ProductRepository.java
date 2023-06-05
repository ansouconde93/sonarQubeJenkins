package com.example.sonarqubejenkins.dao;

import com.example.sonarqubejenkins.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
