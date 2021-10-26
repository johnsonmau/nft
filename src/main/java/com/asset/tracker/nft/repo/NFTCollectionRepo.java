package com.asset.tracker.nft.repo;

import com.asset.tracker.nft.model.NFTCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NFTCollectionRepo extends JpaRepository<NFTCollection, Long> {
}
