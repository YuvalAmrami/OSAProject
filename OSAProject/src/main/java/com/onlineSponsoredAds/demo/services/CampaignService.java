package com.onlineSponsoredAds.demo.services;

import com.onlineSponsoredAds.demo.entities.Campaign;

import java.util.List;

public interface CampaignService {

    List<Campaign> listCampaigns();

    Campaign findCampaign(Long id);
}
