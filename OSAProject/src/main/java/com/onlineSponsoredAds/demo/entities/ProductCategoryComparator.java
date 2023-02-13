package com.onlineSponsoredAds.demo.entities;

import java.util.Comparator;

public class ProductCategoryComparator implements Comparator<Product> {
    public int compare(Product a, Product b)
    {
        return a.getCategory().compareTo(b.getCategory());
    }
}


