package com.asset.tracker.nft.repo;

import com.asset.tracker.nft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    void deleteByEmail(String email);

}
