package com.onlineSponsoredAds.demo.repositories;

import com.onlineSponsoredAds.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, String> {
}
