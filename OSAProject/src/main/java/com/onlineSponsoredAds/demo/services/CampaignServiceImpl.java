package com.onlineSponsoredAds.demo.services;

import com.onlineSponsoredAds.demo.entities.Campaign;
import com.onlineSponsoredAds.demo.entities.CampaignComparator;
import com.onlineSponsoredAds.demo.entities.CampaignDetailsRequestModel;
import com.onlineSponsoredAds.demo.entities.Product;
import com.onlineSponsoredAds.demo.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.onlineSponsoredAds.demo.globalVars.globalVariables.DaysLengthOfCampaign;
import static com.onlineSponsoredAds.demo.globalVars.globalVariables.millisecInDay;


@Service
public class CampaignServiceImpl implements CampaignService{

    @Autowired
    private CampaignRepository campaignRepository ;

    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) { this.productService = productService; }

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

    @Override
    public Campaign createCampaign (Campaign campaign){
        try {
            return campaignRepository.save(campaign);
        } catch ( RuntimeException e){
            throw e;
        }
    }
    @Override
    public void deleteById(Long id){
        try {
            campaignRepository.deleteById(id);
        } catch ( RuntimeException e){
            throw e;
        }
    }


    @Override
    public Campaign createCampaignFromDetailsModole(CampaignDetailsRequestModel campaignDetailsRequestModel){
        List<Product> Products = new ArrayList<Product>();
        String[] ProductsNames = campaignDetailsRequestModel.getProducts();
        for (int i = 0; i <ProductsNames.length; i++){
            Product product = productService.findProduct(ProductsNames[i]);
            Products.add(product);
        }
        Campaign campaign = new Campaign(campaignDetailsRequestModel.getName(),campaignDetailsRequestModel.getStart_date(),
                campaignDetailsRequestModel.getBid(),Products);
        try {
            return campaignRepository.save(campaign);
        } catch ( RuntimeException e){
            throw e;
        }
    }

    public Product ServeAd(String category){
        Date Now = new Date();
        try {
            List<Campaign> categoryCampaign = listCampaigns();
            //possible optimization in the future - cashing the sorted values.
            Collections.sort(categoryCampaign, new CampaignComparator().reversed());
            Campaign highstBidActive = null;
            for (int i =0;i<categoryCampaign.size() ;i++){
                Campaign campaign = categoryCampaign.get(i);
                if (campaign.getStart_date().before(Now) && campaign.getEnd_date().after(Now)){
                    if (highstBidActive==null){
                        highstBidActive = campaign;
                    }
                    Product product = campaign.getProductWithCategory(category);
                    if (product!=null) {
                        return product;
                    }
                }
            }
            if (highstBidActive==null){ //when no campaign is active returns the highest paying campaign
                return categoryCampaign.get(0).getProducts().get(0);
            } else { // when there are no products in the category.
                return highstBidActive.getProducts().get(0);
            }
        }
        catch( RuntimeException e) {
            throw e;
        }
    }

}

