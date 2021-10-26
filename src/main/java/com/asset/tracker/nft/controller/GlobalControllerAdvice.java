package com.asset.tracker.nft.controller;

import com.asset.tracker.nft.model.User;
import com.asset.tracker.nft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UserService userService;

    @ModelAttribute("loggedInUser")
    public User getLoggedInUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String emailAddr = loggedInUser.getName();
        User user = userService.findByEmail(emailAddr);
        return user;
    }

}
