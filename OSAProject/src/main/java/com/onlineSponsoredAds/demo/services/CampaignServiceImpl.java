package com.onlineSponsoredAds.demo.services;

import com.onlineSponsoredAds.demo.entities.Campaign;
import com.onlineSponsoredAds.demo.entities.Product;
import com.onlineSponsoredAds.demo.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CampaignServiceImpl implements CampaignService{

    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public List<Campaign> listCampaigns() {
        return (List<Campaign>) campaignRepository.findAll();
    }

    @Override
    public Campaign findCampaign(Long id) {
        Optional<Campaign> optionalCampaign = campaignRepository.findById(id);

        if(optionalCampaign.isPresent())
            return optionalCampaign.get();
        else
            throw new RuntimeException("Campaign Not Found");
    }




}

