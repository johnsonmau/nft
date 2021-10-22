package com.asset.tracker.nft.service;

import com.asset.tracker.nft.model.OSResponse;
import com.asset.tracker.nft.model.ResponseData;

public interface OSService {

    OSResponse callService(String ethAddress);

}
