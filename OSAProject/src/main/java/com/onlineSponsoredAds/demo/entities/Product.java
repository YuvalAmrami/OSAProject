package com.onlineSponsoredAds.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name="product_serial")
    private String product_serial;

    @Column(nullable = false)
    private String title;

    private String category;

    @Column(nullable = false)
    private Float price;

    public Product(){
    }

    public Product(String title, String category, Float price){
        this.title = title;
        this.category = category;
        this.price = price;
    }
    public Product(String product_serial,String title, String category, Float price){
        this.product_serial = product_serial;
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public String getProduct_serial() {
        return product_serial;
    }

    public void setProduct_serial(String product_serial) {
        this.product_serial = product_serial;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "product{" +
                "product_serial=" + product_serial +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", price='" + price + '\'' +
                '}';
    }


}
