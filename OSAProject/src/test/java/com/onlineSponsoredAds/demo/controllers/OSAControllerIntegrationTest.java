package com.onlineSponsoredAds.demo.controllers;

import com.onlineSponsoredAds.demo.entities.Campaign;
import com.onlineSponsoredAds.demo.entities.CampaignDetailsRequestModel;
import com.onlineSponsoredAds.demo.entities.Product;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OSAControllerIntegrationTest {

    @Autowired
    CampaignController campaignController;

    @Autowired
    ProductController productController;

    @Autowired
    OSAController OSAApiController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testaddProduct() {
        Product Product1 = new Product("A12", "Pikachu plush", "Toys", 120.0F);

        ResponseEntity<Product> Product1Result = productController.addProduct(Product1);
        Assertions.assertThat(Product1Result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(Product1Result.getBody()).toString().equals(Product1.toString());

    }

    @Test
    public void testaddCampaign() {

        Product Product1 = new Product("A12", "Pikachu plush", "Toys", 120.0F);
        Product Product3 = new Product("B27", "Pikachu shirt", "Clothing", 85.0F);

        productController.addProduct(Product1);
        productController.addProduct(Product3);

        List<Product> Products = Arrays.asList(Product1, Product3);
        Campaign campaigns1 = new Campaign(0L, "Pikachu campaign", new Date(2023, 02, 01), 500F, Products);

        ResponseEntity<Campaign> campaign1Result = campaignController.addCampaign(campaigns1);
        Assertions.assertThat(campaign1Result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(campaign1Result.getBody()).toString().equals(campaigns1.toString());
    }

    @Test
    public void testCreateCampaign() {
        Product Product1 = new Product("A18", "Pikachu plush2", "Toys", 120.0F);
        Product Product3 = new Product("B25", "Pikachu shirt2", "Clothing", 85.0F);

        productController.addProduct(Product1);
        productController.addProduct(Product3);

        List<Product> Products = Arrays.asList(Product1, Product3);
        Campaign campaigns1 = new Campaign(0L, "Pikachu campaign", new Date(2023, 02, 01), 500F, Products);

        CampaignDetailsRequestModel campaignDetailsRequestModel = new CampaignDetailsRequestModel("Pikachu campaign", new Date(2023, 02, 01),
                500F, new String[]{"A18", "B25"});

        ResponseEntity<Campaign> campaignDetailsRequestModelResult = OSAApiController.createCampaign(campaignDetailsRequestModel);
        Assertions.assertThat(campaignDetailsRequestModelResult.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(campaignDetailsRequestModelResult.getBody().getClass()).isEqualTo(campaigns1.getClass());
        Assertions.assertThat(campaignDetailsRequestModelResult.getBody()).toString().equals(campaigns1.toString());

        CampaignDetailsRequestModel campaignDetailsRequestModel2 = new CampaignDetailsRequestModel("Pikachu plush campaign", new Date(2023, 02, 01),
                500F, new String[]{"A18"});
        ResponseEntity<Campaign> campaignDetailsRequestModelResult2 = OSAApiController.createCampaign(campaignDetailsRequestModel2);
        Assertions.assertThat(campaignDetailsRequestModelResult2.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(campaignDetailsRequestModelResult2.getBody().getClass()).isEqualTo(campaigns1.getClass());
        Assertions.assertThat(!(campaignDetailsRequestModelResult.getBody()).toString().equals(campaigns1.toString()));
    }

}





