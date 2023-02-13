package com.onlineSponsoredAds.demo.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


//// the Entity that fits to the input specification
@Entity
public class CampaignDetailsRequestModel{

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long CampaignDetailsRequestModel_id;

    private String name;
    private Date start_date;

    private Float bid;

    private String[] products;

    public CampaignDetailsRequestModel(){
        this.CampaignDetailsRequestModel_id = (long) (Math.random()*100000);
    };

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

    public Float getBid() {
        return bid;
    }

    public void setBid(Float bid) {
        this.bid = bid;
    }

    public String[] getProducts() {
        return products;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "name=" + name +
                ", start_date='" + start_date + '\'' +
                ", bid=" + bid +
                ", products='" + products + '\'' +
                '}';
    }

}
