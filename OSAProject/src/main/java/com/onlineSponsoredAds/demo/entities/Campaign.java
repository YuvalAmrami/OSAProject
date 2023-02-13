package com.onlineSponsoredAds.demo.entities;

import jakarta.persistence.*;

import java.util.*;

import static com.onlineSponsoredAds.demo.globalVars.globalVariables.*;


@Entity
@Table( name = "campaigns")
public class Campaign {

    @Id
    private Long campaign_id;

    @Column(nullable = false)
    private String name;

    @Column(name="start_date", nullable = false)
    private Date start_date;

    @Transient
    private Date end_date;

    @Column(nullable = false)
    private Float bid;

    @ManyToMany
    @JoinTable(
            name = "campaign_products",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "product_serial")
    )
    private List<Product> products;

    public Campaign(){
            this.campaign_id = (long) (Math.random()*100000);
    }
    public Campaign(Campaign campaign){
        this.campaign_id = (long) (Math.random()*100000);
        this.name = campaign.name;
        this.start_date = campaign.start_date;
        this.end_date = new Date(start_date.getTime() + millisecInDay*DaysLengthOfCampaign);
        this.bid = campaign.bid;
        this.products = campaign.products;
    }
    public Campaign(String name, Date start_date, Float bid, List<Product> products){
        this.campaign_id = (long) (Math.random()*100000);
        this.name = name;
        this.start_date = start_date;
        this.end_date = new Date(start_date.getTime() + millisecInDay*DaysLengthOfCampaign);
        this.bid = bid;
        this.products = products;
    }

    public Campaign(Long id,String name, Date start_date, Float bid, List<Product> products){
        this.campaign_id = id;
        this.name = name;
        this.start_date = start_date;
        this.end_date = new Date(start_date.getTime() + millisecInDay*DaysLengthOfCampaign);
        this.bid = bid;
        this.products = products;
    }

    public Long getId() {
        return campaign_id;
    }

    public void setId(Long id) {
        this.campaign_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
       if (end_date ==null){
           end_date = new Date(start_date.getTime() + millisecInDay*DaysLengthOfCampaign);
       }
        return end_date;
    }

    public void setEnd_date() {
        this.end_date = new Date(start_date.getTime() + millisecInDay*DaysLengthOfCampaign);
    }

    public Float getBid() {
        return bid;
    }

    public void setBid(Float bid) {
        this.bid = bid;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // possible optimization: adding a categories-set of all Product categories (O(n) Product.size = n)
    // and sort List<Product> by category (O(n*log(n)))
    // implement a binary search of category over List<Product> and returning a relevant Product (O(log(n)))
    // this will be faster only if the sorting and categories set will be cached and will not be running on every call
    // since current run is O(n) and the new proposal will be O(n)+ O(n*log(n)) +O(log(n)) =O(n*log(n))
    // but with caching for the average of m runs: current run  O(n) and the new proposal will be O(log(n))
    public Product getProductWithCategory(String category) {
        for (int i =0; i<products.size();i++){
            Product product = products.get(i);
            if(product.getCategory().equals(category)){
                return product;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaign_id " + campaign_id + '\'' +
                "name=" + name +
                ", start_date='" + start_date + '\'' +
                ", bid=" + bid +
                ", products='" + products + '\'' +
                '}';
    }

}
