package com.nvdevelopers.e_krishi.controller;

import com.nvdevelopers.e_krishi.request.dealer.*;
import com.nvdevelopers.e_krishi.service.DealerService;
import com.nvdevelopers.e_krishi.service.DealerVegetablesService;
import com.nvdevelopers.e_krishi.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "${url.base.dealer}")
public class DealerController {

    @Autowired
    private DealerService dealerService;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private DealerVegetablesService dealerVegetablesService;

    @RequestMapping(value = "${url.dealer.register}", method = RequestMethod.POST)
    public ResponseEntity<?> processRegisterRequest(@Valid @RequestBody RegisterRequest registerRequest) {
        return dealerService.register(registerRequest);
    }

    @RequestMapping(value = "${url.dealer.authenticate}", method = RequestMethod.POST)
    public ResponseEntity<?> processAuthenticationRequest(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return dealerService.authenticate(authenticationRequest);
    }

    @RequestMapping(value = "${url.dealer.add_vegetables_by_name}", method = RequestMethod.POST)
    public ResponseEntity<?> processAddVegetablesByNameRequest(@Valid @RequestBody AddVegetablesByNameRequest addVegetablesByNameRequest) {
        return dealerVegetablesService.addVegetablesByName(addVegetablesByNameRequest);
    }

    @RequestMapping(value = "${url.dealer.add_vegetables_by_id}", method = RequestMethod.POST)
    public ResponseEntity<?> processAddVegetablesByIdRequest(@Valid @RequestBody AddVegetablesByIdRequest addVegetablesByIdRequest) {
        return dealerVegetablesService.addVegetablesById(addVegetablesByIdRequest);
    }

    @RequestMapping(value = "${url.dealer.get_dealer_vegetables}", method = RequestMethod.POST)
    public ResponseEntity<?> processGetDealerVegetablesRequest(@Valid @RequestBody GetDealerVegetablesRequest getDealerVegetablesRequest) {
        return dealerService.getDealerVegetables(getDealerVegetablesRequest);
    }

    @RequestMapping(value = "${url.dealer.logout}", method = RequestMethod.GET)
    public ResponseEntity<?> processLogoutRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }

        return ResponseEntity.ok("Logout Successful");
    }
}
