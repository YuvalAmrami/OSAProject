package com.onlineSponsoredAds.demo.services;

import com.onlineSponsoredAds.demo.entities.Product;
import com.onlineSponsoredAds.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{


    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product findProduct(String product_serial) {
        Optional<Product> optionalProduct = productRepository.findById(product_serial);

        if(optionalProduct.isPresent())
            return optionalProduct.get();
        else
            throw new RuntimeException("Product Not Found");
    }

    @Override
    public Product createProduct (Product product){
        try {
            return productRepository.save(product);
        } catch ( RuntimeException e){
            throw e;
        }
    }

    @Override
    public void deleteById(String product_serial){
        try {
            productRepository.deleteById(product_serial);
        } catch ( RuntimeException e){
            throw e;
        }
    }


}
