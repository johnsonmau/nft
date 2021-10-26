package com.asset.tracker.nft.service;

import com.asset.tracker.nft.model.NFTCollection;
import com.asset.tracker.nft.model.Portfolio;
import com.asset.tracker.nft.repo.NFTCollectionRepo;
import com.asset.tracker.nft.repo.PortfolioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NFTCollectionServiceImpl implements NFTCollectionService {

    @Autowired
    private NFTCollectionRepo nftCollectionRepo;

    @Override
    public NFTCollection save(NFTCollection nftCollection) {
        return nftCollectionRepo.save(nftCollection);
    }

    @Override
    public void deleteCollectionById(Long id) {
        nftCollectionRepo.deleteById(id);
    }
}
