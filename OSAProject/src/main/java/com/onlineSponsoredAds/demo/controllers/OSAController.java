package com.onlineSponsoredAds.demo.controllers;


import com.onlineSponsoredAds.demo.entities.Campaign;
import com.onlineSponsoredAds.demo.entities.CampaignDetailsRequestModel;
import com.onlineSponsoredAds.demo.entities.Product;
import com.onlineSponsoredAds.demo.services.CampaignService;
import com.onlineSponsoredAds.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/APIs")
public class OSAController {

    private CampaignService campaignService;

    private ProductService productService;

    @Autowired
    public void setCampaignService(CampaignService campaignService) { this.campaignService = campaignService; }
    @Autowired
    public void setProductService(ProductService productService) { this.productService = productService; }

// ------------------------------------------------Create campaign

    @PostMapping(path = "/CreateCampaign")
    public ResponseEntity<Campaign> createCampaign(@RequestBody CampaignDetailsRequestModel campaignDetailsRequestModel) {
        try {
            return new ResponseEntity<Campaign>(campaignService.detailsModoleCreateCampaign(campaignDetailsRequestModel), HttpStatus.OK);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaign Not Found");
        }
    }


// ------------------------------------------------Serve Ad
//    @PostMapping(path = "/ServeAd/{Category}")
//    public ResponseEntity<Product> ServeAd(@PathVariable("Category") String Category) {
//        try {
//            return new ResponseEntity<Product>(campaignService.detailsModoleCreateCampaign(campaignDetailsRequestModel), HttpStatus.OK);
//        } catch (RuntimeException exception) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campaign Not Found");
//        }
//}



}
