package com.onlineSponsoredAds.demo.controllers;

import com.onlineSponsoredAds.demo.entities.Campaign;
import com.onlineSponsoredAds.demo.entities.Product;
import com.onlineSponsoredAds.demo.globalVars.CampaignNotFoundException;
import com.onlineSponsoredAds.demo.repositories.CampaignRepository;
import com.onlineSponsoredAds.demo.services.CampaignService;
import com.onlineSponsoredAds.demo.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/Campaigns")
public class CampaignController {

    private CampaignService campaignService;


    @Autowired
    public void setCampaignService(CampaignService campaignService) { this.campaignService = campaignService; }

    @GetMapping
    public ResponseEntity<List<Campaign>> getAllCampaign() {
        List<Campaign> list = campaignService.listCampaigns();
        return new ResponseEntity<List<Campaign>>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Campaign> getCampaign(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<Campaign>(campaignService.findCampaign(id), HttpStatus.OK);
        } catch (CampaignNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<Campaign> addCampaign(@RequestBody Campaign campaign) {
        return new ResponseEntity<Campaign>(campaignService.createCampaign(campaign), HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        campaignService.deleteById(id);
    }


}
