package com.nvdevelopers.e_krishi.service;

import com.nvdevelopers.e_krishi.mapper.DealerMapper;
import com.nvdevelopers.e_krishi.model.Dealer;
import com.nvdevelopers.e_krishi.model.DealerVegetables;
import com.nvdevelopers.e_krishi.repository.DealerRepository;
import com.nvdevelopers.e_krishi.repository.DealerVegetablesRepository;
import com.nvdevelopers.e_krishi.request.dealer.AuthenticationRequest;
import com.nvdevelopers.e_krishi.request.dealer.GetDealerVegetablesRequest;
import com.nvdevelopers.e_krishi.request.dealer.RegisterRequest;
import com.nvdevelopers.e_krishi.response.dealer.AuthenticationResponse;
import com.nvdevelopers.e_krishi.response.dealer.GetDealerVegetablesResponse;
import com.nvdevelopers.e_krishi.response.dealer.RegisterResponse;
import com.nvdevelopers.e_krishi.util.JwtToken;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class DealerService {

    @Autowired
    private DealerMapper dealerMapper;

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private DealerVegetablesRepository dealerVegetablesRepository;

    @Autowired
    private JwtToken jwtToken;

    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        log.info("Dealer register request with data {}", registerRequest);

        Dealer dealer = dealerMapper.mapToDealer(registerRequest);
        dealer.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        RegisterResponse registerResponse = new RegisterResponse();

        try {
            dealerRepository.save(dealer);
            registerResponse.setStatus(true);
            registerResponse.setResponse("Dealer registration successful");
        } catch (DataIntegrityViolationException ex) {
            log.error("Exception occurred while registering the dealer {}", ex.getMessage());
            registerResponse.setStatus(false);
            registerResponse.setResponse("Account already exists");
        }

        log.info("Sending response to dealer register request with {}", registerResponse);
        return ResponseEntity.ok(registerResponse);
    }

    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest) {
        log.info("Dealer authentication request with data {}", authenticationRequest);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (DisabledException | BadCredentialsException ex) {
            log.error("Exception while authenticating user {}", ex.getMessage());
            authenticationResponse.setStatus(false);
            authenticationResponse.setResponse(ex.getMessage());
            return ResponseEntity.ok(authenticationResponse);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtToken.generateToken(userDetails);

        authenticationResponse.setStatus(true);
        authenticationResponse.setToken(token);
        authenticationResponse.setDealer(dealerMapper.mapToAuthenticationResponseDealer(userDetailsService.getDealer()));

        log.info("Sending response to authentication request with {}", authenticationResponse);
        return ResponseEntity.ok(authenticationResponse);
    }

    public ResponseEntity<?> getDealerVegetables(GetDealerVegetablesRequest getDealerVegetablesRequest) {
        log.info("Get dealer vegetables request with data {}", getDealerVegetablesRequest);
        List<DealerVegetables> dealerVegetablesList = dealerVegetablesRepository.findByDealerEmail(getDealerVegetablesRequest.getEmail());

        GetDealerVegetablesResponse getDealerVegetablesResponse = new GetDealerVegetablesResponse();
        List<GetDealerVegetablesResponse.DealerVegetables> dealerVegetables = new ArrayList<>();

        dealerVegetablesList.forEach(dealerVegetable -> {
            dealerVegetables.add(dealerMapper.mapToGetDealerVegetablesResponse(dealerVegetable));
        });

        getDealerVegetablesResponse.setDealerVegetables(dealerVegetables);

        log.info("Sending response to get dealer vegetables request with {}", getDealerVegetablesResponse);
        return ResponseEntity.ok(getDealerVegetablesResponse);
    }
}
