package com.asset.tracker.nft.model;

import java.util.List;

public class Portfolio {

    private String owner;
    private double amount;
    private List<NFTCollection> collection;

    public Portfolio(String owner, double amount, List<NFTCollection> collection) {
        this.owner = owner;
        this.amount = amount;
        this.collection = collection;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<NFTCollection> getCollection() {
        return collection;
    }

    public void setCollection(List<NFTCollection> collection) {
        this.collection = collection;
    }
}
