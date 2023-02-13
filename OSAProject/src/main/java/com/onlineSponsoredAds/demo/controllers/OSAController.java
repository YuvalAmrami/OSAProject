package com.onlineSponsoredAds.demo.controllers;


import com.onlineSponsoredAds.demo.entities.Campaign;
import com.onlineSponsoredAds.demo.entities.CampaignDetailsRequestModel;
import com.onlineSponsoredAds.demo.entities.Product;
import com.onlineSponsoredAds.demo.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/APIs")
public class OSAController {

    private CampaignService campaignService;

    @Autowired
    public void setCampaignService(CampaignService campaignService) { this.campaignService = campaignService; }


// ------------------------------------------------Create campaign

    @PostMapping(path = "/CreateCampaign")
    public ResponseEntity<Campaign> createCampaign(@RequestBody CampaignDetailsRequestModel campaignDetailsRequestModel) {
        try {
            return new ResponseEntity<Campaign>(campaignService.createCampaignFromDetailsModole(campaignDetailsRequestModel), HttpStatus.OK);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Campaign Was Not Created");
        }
    }


// ------------------------------------------------Serve Ad
    @GetMapping(path = "/ServeAd/{Category}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> ServeAd(@PathVariable("Category") String category) {
        try {
            return new ResponseEntity<Product>(campaignService.ServeAd(category), HttpStatus.OK);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        }
}



}
