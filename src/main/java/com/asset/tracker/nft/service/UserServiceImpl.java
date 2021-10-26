package com.asset.tracker.nft.service;

import com.asset.tracker.nft.model.*;
import com.asset.tracker.nft.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private NFTCollectionService nftCollectionService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private OSService osService;

    @Override
    public User createUser(UserRegistrationDto userRegistrationDto) {
        List<NFTCollection> nftCollections = new ArrayList<>();
        Portfolio portfolio = new Portfolio(0, nftCollections);
        portfolioService.save(portfolio);
        User user = new User(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(), userRegistrationDto.getUsername(),
                userRegistrationDto.getEmail(), passwordEncoder.encode(userRegistrationDto.getPassword()),
                portfolio, Arrays.asList(new Role("ROLE_USER")));
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(String email){
        userRepo.deleteByEmail(email);
    }

    @Override
    public User addCollectionToPortfolio(User loggedInUser, String nftAddr, Long quantity){
        Portfolio portfolio = loggedInUser.getPortfolio();
        List<NFTCollection> nftCollections = portfolio.getCollection();
        OSResponse osResponse = osService.callService(nftAddr);
        NFTCollection nftCollection = new NFTCollection(osResponse.getAssetContract().getName(),
                osResponse.getAssetContract().getAddress(), osResponse.getCollection().getStats().getFloorPrice(), quantity);

        List<Portfolio> portfolios = nftCollection.getPortfolios();
        if (portfolios == null){
            portfolios = new ArrayList<>();
            portfolios.add(portfolio);
        } else {
            portfolios.add(portfolio);
        }
        nftCollection.setPortfolios(portfolios);

        nftCollections.add(nftCollection);
        nftCollectionService.save(nftCollection);

        portfolio.setAmount(portfolio.getAmount()+(nftCollection.getFloorPrice()*quantity));
        portfolioService.save(portfolio);

        return userRepo.save(loggedInUser);
    }

    @Override
    public void deleteCollectionFromPortfolio(User loggedInUser, Long id) {
        Portfolio portfolio = loggedInUser.getPortfolio();
        List<NFTCollection> nftCollections = portfolio.getCollection();
        for (int i = 0; i < nftCollections.size(); i++){
            if (nftCollections.get(i).getId() == id){
                portfolio.setAmount(portfolio.getAmount()-(nftCollections.get(i).getFloorPrice()
                        *nftCollections.get(i).getQuantity()));
                nftCollections.remove(i);
                break;
            }
        }
        nftCollectionService.deleteCollectionById(id);
        portfolioService.save(portfolio);
        userRepo.save(loggedInUser);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
