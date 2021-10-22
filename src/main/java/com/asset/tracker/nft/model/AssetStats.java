package com.asset.tracker.nft.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssetStats {

    @JsonProperty("floor_price")
    private double floorPrice;

    public AssetStats() {
    }

    public double getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(double floorPrice) {
        this.floorPrice = floorPrice;
    }
}
