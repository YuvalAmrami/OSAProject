package com.onlineSponsoredAds.demo.controllers;

import com.onlineSponsoredAds.demo.entities.Campaign;
import com.onlineSponsoredAds.demo.entities.Product;
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
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Application Not Found");
        }
    }



//    @Autowired
//    private CampaignRepository campaignRepository;
//
//
//    @GetMapping
//    public List<Campaign> list(){
//        return campaignRepository.findAll();
//    }
//
//    @GetMapping
//    @RequestMapping("{id}")
//    public Campaign get(@PathVariable Long id){
//        return campaignRepository.getOne(id);
//    }
//
//    @PostMapping //the spring system is automatically composing the body to a Campaign
//    public Campaign create(@RequestBody final Campaign Campaign){
//        return campaignRepository.saveAndFlush(Campaign);
//    }
//
//    //    @DeleteMapping
//    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//    public void delete(@PathVariable Long id) {
//        campaignRepository.deleteById(id);
//    }
//
//    //    @PutMapping
//    @RequestMapping(value = "{id}" , method = RequestMethod.PUT)
//    public Campaign update(@PathVariable Long id, @RequestBody Campaign Campaign){
//        Campaign existingCampaign = campaignRepository.getOne(id);
//        BeanUtils.copyProperties(Campaign,existingCampaign,"campaign_id");
//        return campaignRepository.saveAndFlush(existingCampaign);
//    }
//
    
    
}
