package com.nvdevelopers.e_krishi.service;

import com.nvdevelopers.e_krishi.model.Dealer;
import com.nvdevelopers.e_krishi.repository.DealerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Data
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private DealerRepository dealerRepository;

    private Dealer dealer;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        dealer = dealerRepository.findUserByEmail(username);
        if (dealer == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        return new User(dealer.getEmail(), dealer.getPassword(), new ArrayList<>());
    }

    public Dealer findDealerByEmail(String email) {
        Dealer dealer = dealerRepository.findUserByEmail(email);
        if (dealer == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return dealer;
    }
}
