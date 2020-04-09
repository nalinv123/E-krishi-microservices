package com.nvdevelopers.e_krishi.controller;

import com.nvdevelopers.e_krishi.request.farmer.SearchVegetableRequest;
import com.nvdevelopers.e_krishi.service.FarmerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "${url.base.farmer}")
@Log4j2
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @RequestMapping(value = "${url.farmer.search.vegetable}", method = RequestMethod.POST)
    public ResponseEntity<?> processSearchVegetableRequest(@Valid @RequestBody SearchVegetableRequest searchVegetableRequest) {
        log.info("Received search vegetable by name and city with {}", searchVegetableRequest.toString());
        return farmerService.searchVegetable(searchVegetableRequest);
    }
}
