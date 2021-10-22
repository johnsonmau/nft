package com.asset.tracker.nft.model;

public class NFTCollection {

    private String name;
    private String ethAddress;
    private double floorPrice;

    public NFTCollection(String name, String ethAddress, double floorPrice) {
        this.name = name;
        this.ethAddress = ethAddress;
        this.floorPrice = floorPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEthAddress() {
        return ethAddress;
    }

    public void setEthAddress(String ethAddress) {
        this.ethAddress = ethAddress;
    }

    public double getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(double floorPrice) {
        this.floorPrice = floorPrice;
    }
}
