package com.onlineSponsoredAds.demo.services;

import com.onlineSponsoredAds.demo.entities.Campaign;
import com.onlineSponsoredAds.demo.entities.CampaignDetailsRequestModel;
import com.onlineSponsoredAds.demo.entities.Product;
import com.onlineSponsoredAds.demo.repositories.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
//        System.out.println("campaign: ");
        System.out.println(campaign);
        try {
            return campaignRepository.save(campaign);
        } catch ( RuntimeException e){
            throw e;
        }
    }

    @Override
    public Campaign detailsModoleCreateCampaign(CampaignDetailsRequestModel campaignDetailsRequestModel){
        System.out.println("CampaignDetailsRequestModel: ");
        System.out.println(campaignDetailsRequestModel);
        List<Product> Products = new ArrayList<Product>();
        String[] ProductsNames = campaignDetailsRequestModel.getProducts();
        for (int i = 0; i <ProductsNames.length; i++){
            Product product = productService.findProduct(ProductsNames[i]);
            System.out.println("product: "+product.toString());
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
//    public
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

