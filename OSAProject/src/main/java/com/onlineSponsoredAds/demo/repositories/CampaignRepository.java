package com.onlineSponsoredAds.demo.repositories;

import com.onlineSponsoredAds.demo.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
