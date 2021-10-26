package com.asset.tracker.nft.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="amount")
    private double amount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "collection",
            joinColumns = @JoinColumn(
                    name = "portfolio_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "collection_id", referencedColumnName = "id"))
    private List<NFTCollection> collection;

    public Portfolio() {
    }

    public Portfolio(double amount, List<NFTCollection> collection) {
        this.amount = amount;
        this.collection = collection;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<NFTCollection> getCollection() {
        return collection;
    }

    public void setCollection(List<NFTCollection> collection) {
        this.collection = collection;
    }
}
