package com.onlineSponsoredAds.demo.services;

import com.onlineSponsoredAds.demo.entities.Campaign;
import com.onlineSponsoredAds.demo.entities.CampaignDetailsRequestModel;
import com.onlineSponsoredAds.demo.entities.Product;

import java.util.List;

public interface CampaignService {

    List<Campaign> listCampaigns();

    Campaign findCampaign(Long id);

    Campaign createCampaign (Campaign campaign);

    void deleteById(Long id);

    Campaign createCampaignFromDetailsModel(CampaignDetailsRequestModel campaignDetailsRequestModel);

    Product serveAd(String category);

}
