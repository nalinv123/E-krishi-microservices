package com.nvdevelopers.e_krishi.service;

import com.nvdevelopers.e_krishi.mapper.FarmerMapper;
import com.nvdevelopers.e_krishi.model.DealerVegetables;
import com.nvdevelopers.e_krishi.repository.DealerVegetablesRepository;
import com.nvdevelopers.e_krishi.request.farmer.SearchVegetableRequest;
import com.nvdevelopers.e_krishi.response.farmer.SearchVegetableResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class FarmerService {

    @Autowired
    private DealerVegetablesRepository dealerVegetablesRepository;

    @Autowired
    private FarmerMapper farmerMapper;

    public ResponseEntity<?> searchVegetable(SearchVegetableRequest searchVegetableRequest) {
        List<DealerVegetables> dealerVegetablesList = dealerVegetablesRepository.findByVegetablesNameAndDealerCity(searchVegetableRequest.getName(), searchVegetableRequest.getCity());

        SearchVegetableResponse searchVegetableResponse = new SearchVegetableResponse();
        List<SearchVegetableResponse.DealerVegetables> dealerVegetablesList1 = new ArrayList<>();

        dealerVegetablesList.forEach(dealerVegetables -> {
            dealerVegetablesList1.add(farmerMapper.mapToSearchVegetableResponse(dealerVegetables));
        });
        searchVegetableResponse.setDealerVegetables(dealerVegetablesList1);

        log.info("Sending response to search vegetable by name and city request with {}", searchVegetableResponse.toString());
        return ResponseEntity.ok(searchVegetableResponse);
    }
}
