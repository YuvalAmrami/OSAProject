package com.onlineSponsoredAds.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_serial")
    private String product_serial;

    private String title;

    private String category;

    private Float price;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Campaign> campaigns;


    public Product(){
    }

    public Product(String title, String category, Float price){
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

}
