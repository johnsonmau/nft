package com.asset.tracker.nft.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nft_collection")
public class NFTCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "eth_address")
    private String ethAddress;

    @Column(name="floor_price")
    private double floorPrice;

    @Column(name="quantity")
    private long quantity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "portfolios",
            joinColumns = @JoinColumn(
                    name = "collection_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "portfolio_id", referencedColumnName = "id"))
    private List<Portfolio> portfolios;

    public NFTCollection() {
    }

    public NFTCollection(String name, String ethAddress, double floorPrice, long quantity) {
        this.name = name;
        this.ethAddress = ethAddress;
        this.floorPrice = floorPrice;
        this.quantity = quantity;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }
}
