package com.onlineSponsoredAds.demo.entities;

import java.util.Comparator;

public class CampaignComparator implements Comparator<Campaign> {

    public int compare(Campaign a, Campaign b)
    {
        return (int) (a.getBid() - b.getBid());
    }

}
