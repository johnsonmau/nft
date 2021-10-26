package com.asset.tracker.nft.controller;

import com.asset.tracker.nft.model.*;
import com.asset.tracker.nft.service.OSService;
import com.asset.tracker.nft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MiscController {

    @Autowired
    private OSService osService;

    @Autowired
    private UserService userService;

    private String addAddrConError = "";

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @ModelAttribute("addAddrError")
    public String getErr() {
        return addAddrConError;
    }

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/collection")
    public String getCollection(@RequestParam(required = false) String addr, Model model){
        if (addr != null){
            model.addAttribute("addr", addr);
            OSResponse osResponse = osService.callService(addr);
            if (osResponse != null) {
                model.addAttribute("osResponse", osResponse);
            } else {
                model.addAttribute("error", "Sorry, we do not support that NFT collection");
            }
        }

        return "collection";
    }

    @GetMapping("/dashboard")
    public String getDash() {
        return "dashboard";
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping(value = "/add/collection")
    public String addCollection(@RequestParam String nftAddr, @RequestParam String quantity, Authentication authentication){

        nftAddr = nftAddr.trim();
        quantity = quantity.trim();

        boolean invalid = false;

        if (nftAddr == "" && quantity == ""){
            invalid = true;
            addAddrConError = "Address and Quantity are required";
        }
        else if (nftAddr == ""){
            invalid = true;
            addAddrConError = "Address is required";
        }
        else if (quantity == ""){
            invalid = true;
            addAddrConError = "Quantity is required";
        } else {
            try {
                Integer.valueOf(quantity);
            } catch (Exception ex) {
                invalid = true;
                addAddrConError = "Quantity must be a numeric value";
            }
        }

        if (invalid) return "redirect:/dashboard";

        try {
            userService.addCollectionToPortfolio(userService.findByEmail(authentication.getName()), nftAddr, Long.valueOf(quantity));
            addAddrConError = "";
            return "redirect:/dashboard";
        } catch (Exception ex){
            addAddrConError = "Sorry, we do not support the address ["+nftAddr+"]";
            return "redirect:/dashboard";
        }

    }

    @PostMapping(value = "/delete/collection/{id}")
    public String deleteCollection(@PathVariable String id, Authentication authentication){
        try {
            userService.deleteCollectionFromPortfolio(userService.findByEmail(authentication.getName()), Long.valueOf(id));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/deactivate")
    public String deactivateAccount(Authentication authentication){
        String userToDelete = authentication.getName();
        userService.deleteUser(userToDelete);
        return "redirect:/login?deletedAcc";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto userDto,
                                      BindingResult result){

        User emailExists = userService.findByEmail(userDto.getEmail());
        User usernameExists = userService.findByUsername(userDto.getUsername());

        if (emailExists != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (usernameExists != null){
            result.rejectValue("username", null, "There is already an account registered with that username");
        }

        if (userDto.getEmail().equals(userDto.getConfirmEmail()) == false){
            result.rejectValue("email", null, "Email and Confirm Email must match");
        }

        if (userDto.getPassword().equals(userDto.getConfirmPassword()) == false){
            result.rejectValue("password", null, "Password and Confirm Password must match");
        }

        if (userDto.getTerms() == false){
            result.rejectValue("terms", null, "You must agree to the terms and conditions");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userService.createUser(userDto);
        return "redirect:/registration?success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
