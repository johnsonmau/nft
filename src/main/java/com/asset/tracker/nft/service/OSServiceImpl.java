package com.asset.tracker.nft.service;

import com.asset.tracker.nft.model.OSResponse;
import com.asset.tracker.nft.model.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OSServiceImpl implements OSService {

    @Override
    public OSResponse callService(String ethAddress) {
        RestTemplate restTemplate = new RestTemplate();
        String reqUrl = "https://api.opensea.io/api/v1/asset/"+ethAddress+"/1/";
        System.out.println("Requested URL: "+reqUrl);

        try {
            ResponseEntity<OSResponse> osReq = restTemplate.getForEntity(reqUrl, OSResponse.class);
            return osReq.getBody();
        } catch (Exception ex) {
            return null;
        }
    }

}
