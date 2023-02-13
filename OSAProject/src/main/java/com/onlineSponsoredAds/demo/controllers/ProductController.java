package com.onlineSponsoredAds.demo.controllers;

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


//
//    @Autowired
//    private ProductRepository productRepository;
//
//     @GetMapping
//     public List<Product> list(){
//          return productRepository.findAll();
//     }
//
//    @GetMapping
//    @RequestMapping("{id}")
//    public Product get(@PathVariable String id){
//        return productRepository.getOne(id);
//    }
//
//    @PostMapping
//    public Product create(@RequestBody final Product product){
//        return productRepository.saveAndFlush(product);
//    }
//
//    //    @DeleteMapping
//    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//    public void delete(@PathVariable String id) {
//        productRepository.deleteById(id);
//    }
//
//    //    @PutMapping
//    @RequestMapping(value = "{id}" , method = RequestMethod.PUT)
//    public Product update(@PathVariable String id, @RequestBody Product product) {
//        Product existingProduct = productRepository.getOne(id);
//        BeanUtils.copyProperties(product, existingProduct, "product_serial");
//        return productRepository.saveAndFlush(existingProduct);
//    }




}
