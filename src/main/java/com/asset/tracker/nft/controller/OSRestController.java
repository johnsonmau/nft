package com.asset.tracker.nft.controller;

import com.asset.tracker.nft.model.OSResponse;
import com.asset.tracker.nft.model.ResponseData;
import com.asset.tracker.nft.service.OSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OSRestController {

    @Autowired
    private OSService osService;

    @GetMapping(value = "os/data/v1", produces = "application/json")
    public @ResponseBody
    OSResponse getOsData(@RequestParam String addr){
      return osService.callService(addr);
    }
}
