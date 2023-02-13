package com.onlineSponsoredAds.demo.services;

import com.onlineSponsoredAds.demo.entities.Campaign;
import com.onlineSponsoredAds.demo.entities.CampaignDetailsRequestModel;

import java.util.List;

public interface CampaignService {

    List<Campaign> listCampaigns();

    Campaign findCampaign(Long id);

    Campaign createCampaign (Campaign campaign);

    Campaign detailsModoleCreateCampaign(CampaignDetailsRequestModel campaignDetailsRequestModel);

}
