package com.asset.tracker.nft.service;

import com.asset.tracker.nft.model.NFTCollection;

public interface NFTCollectionService {
    NFTCollection save(NFTCollection nftCollection);
    void deleteCollectionById(Long id);
}
