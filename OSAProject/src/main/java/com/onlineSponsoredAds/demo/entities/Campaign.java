package com.onlineSponsoredAds.demo.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

import static com.onlineSponsoredAds.demo.globalVars.globalVariables.*;


@Entity
@Table( name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    Campaign(){
    }
    Campaign(String name, Date start_date, Float bid, List<Product> products){
        this.name = name;
        this.start_date = start_date;
//        this.end_date = new Date(start_date.getTime() + millisecInDay*DaysLengthOfCampaign);
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
       return new Date(start_date.getTime() + millisecInDay*DaysLengthOfCampaign);
    }

//    public void setEnd_date() {
//        this.end_date = new Date(start_date.getTime() + millisecInDay*DaysLengthOfCampaign);
//    }

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
}
