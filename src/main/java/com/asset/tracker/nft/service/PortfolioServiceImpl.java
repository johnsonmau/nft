package com.asset.tracker.nft.service;

import com.asset.tracker.nft.model.Portfolio;
import com.asset.tracker.nft.repo.PortfolioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepo portfolioRepo;

    @Override
    public Portfolio save(Portfolio portfolio) {
        return portfolioRepo.save(portfolio);
    }


}
