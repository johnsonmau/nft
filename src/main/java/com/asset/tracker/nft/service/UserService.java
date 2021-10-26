package com.asset.tracker.nft.service;

import com.asset.tracker.nft.model.User;
import com.asset.tracker.nft.model.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User createUser(UserRegistrationDto userRegistrationDto);
    void deleteUser(String email);
    User addCollectionToPortfolio(User loggedInUser, String nftAddr, Long quantity);
    List<User> getAllUsers();
    void deleteCollectionFromPortfolio(User loggedInUser, Long id);
    User findByUsername(String username);
    User findByEmail(String email);
}
