package com.onlineSponsoredAds.demo.services;

import com.onlineSponsoredAds.demo.entities.Campaign;
import com.onlineSponsoredAds.demo.entities.CampaignComparator;
import com.onlineSponsoredAds.demo.entities.CampaignDetailsRequestModel;
import com.onlineSponsoredAds.demo.entities.Product;
import com.onlineSponsoredAds.demo.globalVars.CampaignNotFoundException;
import com.onlineSponsoredAds.demo.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.onlineSponsoredAds.demo.globalVars.globalVariables.DaysLengthOfCampaign;
import static com.onlineSponsoredAds.demo.globalVars.globalVariables.millisecInDay;


@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Campaign> listCampaigns() {
        return (List<Campaign>) campaignRepository.findAll();
    }

    @Override
    public Campaign findCampaign(Long id) {
        Optional<Campaign> optionalCampaign = campaignRepository.findById(id);

        if (optionalCampaign.isPresent()) return optionalCampaign.get();
        else throw new CampaignNotFoundException("Campaign: "+id+" Not Found");
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public void deleteById(Long id) {
        campaignRepository.deleteById(id);
    }


    @Override
    public Campaign createCampaignFromDetailsModel(CampaignDetailsRequestModel campaignDetailsRequestModel) {
        List<Product> products = Arrays.stream(campaignDetailsRequestModel.getProducts()).map(x -> productService.findProduct(x)).toList();
        Campaign campaign = new Campaign(campaignDetailsRequestModel.getName(), campaignDetailsRequestModel.getStart_date(), campaignDetailsRequestModel.getBid(), products);
        return campaignRepository.save(campaign);
    }


    public Product serveAd(String category) {
        Date Now = new Date();
        List<Campaign> categoryCampaign = listCampaigns();
        //possible optimization in the future - cashing the sorted values.
        Collections.sort(categoryCampaign, new CampaignComparator().reversed());
        Campaign highestBidActive = null;
        for (Campaign campaign: categoryCampaign) {
            if (campaign.getStart_date().before(Now) && campaign.getEnd_date().after(Now)) {
                if (highestBidActive == null) { // the campaign with the highest Bid that is active now
                    highestBidActive = campaign;
                }
                Product product = campaign.getProductWithCategory(category);
                if (product != null) {
                    return product;
                }
            }
        }
        if (highestBidActive != null) { // when there are no products in the category.
            return highestBidActive.getProducts().get(0);
        } else { //when no campaign is active returns the highest paying campaign
            return categoryCampaign.get(0).getProducts().get(0);

        }

    }

}

