package com.onlineSponsoredAds.demo.services;

import com.onlineSponsoredAds.demo.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    List<Product> listProducts();

    Product findProduct(String product_serial);

}
