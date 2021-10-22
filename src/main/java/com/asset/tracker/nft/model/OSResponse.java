package com.asset.tracker.nft.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OSResponse {

    private long id;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("asset_contract")
    private AssetContract assetContract;
    private AssetCollection collection;

    public OSResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AssetContract getAssetContract() {
        return assetContract;
    }

    public void setAssetContract(AssetContract assetContract) {
        this.assetContract = assetContract;
    }

    public AssetCollection getCollection() {
        return collection;
    }

    public void setCollection(AssetCollection collection) {
        this.collection = collection;
    }
}
