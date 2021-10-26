package com.asset.tracker.nft.repo;

import com.asset.tracker.nft.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepo extends JpaRepository<Portfolio, Long> {
}
