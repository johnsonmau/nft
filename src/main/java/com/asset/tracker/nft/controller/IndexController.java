package com.asset.tracker.nft.controller;

import com.asset.tracker.nft.model.OSResponse;
import com.asset.tracker.nft.service.OSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private OSService osService;

    @GetMapping("/")
    public String getIndex(@RequestParam(required = false) String addr, Model model){

        if (addr != null){
            model.addAttribute("addr", addr);
            OSResponse osResponse = osService.callService(addr);
            if (osResponse != null) {
                model.addAttribute("osResponse", osResponse);
            } else {
                model.addAttribute("error", "Sorry, we do not support that NFT collection");
            }
        }

        return "index";
    }
}
