package com.onlineSponsoredAds.demo.controllers;

import com.onlineSponsoredAds.demo.entities.Campaign;
import com.onlineSponsoredAds.demo.entities.Product;
import com.onlineSponsoredAds.demo.repositories.ProductRepository;
import com.onlineSponsoredAds.demo.services.ProductService;
import com.onlineSponsoredAds.demo.services.ProductServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/Products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) { this.productService = productService; }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> list = productService.listProducts();
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<Product>(productService.findProduct(id), HttpStatus.OK);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Application Not Found");
        }
    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            return new ResponseEntity<Product>(productService.createProduct(product), HttpStatus.OK);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Was Not Created");
        }
    }

        //    @DeleteMapping
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        productService.deleteById(id);
    }


}
