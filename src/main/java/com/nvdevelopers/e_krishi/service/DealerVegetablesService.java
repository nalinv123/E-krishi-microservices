package com.nvdevelopers.e_krishi.service;

import com.nvdevelopers.e_krishi.mapper.DealerVegetablesMapper;
import com.nvdevelopers.e_krishi.model.Dealer;
import com.nvdevelopers.e_krishi.model.DealerVegetables;
import com.nvdevelopers.e_krishi.model.Vegetables;
import com.nvdevelopers.e_krishi.repository.DealerRepository;
import com.nvdevelopers.e_krishi.repository.DealerVegetablesRepository;
import com.nvdevelopers.e_krishi.repository.VegetablesRepository;
import com.nvdevelopers.e_krishi.request.dealer.AddVegetablesByIdRequest;
import com.nvdevelopers.e_krishi.request.dealer.AddVegetablesByNameRequest;
import com.nvdevelopers.e_krishi.response.dealer.AddVegetablesByIdResponse;
import com.nvdevelopers.e_krishi.response.dealer.AddVegetablesByNameResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class DealerVegetablesService {

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private DealerVegetablesMapper dealerVegetablesMapper;

    @Autowired
    private VegetablesRepository vegetablesRepository;

    @Autowired
    private DealerVegetablesRepository dealerVegetablesRepository;

    public ResponseEntity<?> addVegetablesByName(AddVegetablesByNameRequest addVegetablesByNameRequest) {
        log.info("Add vegetables by name request with data {}", addVegetablesByNameRequest);
        Dealer dealer = dealerRepository.findUserByEmail(addVegetablesByNameRequest.getDealerEmail());
        AddVegetablesByNameResponse addVegetablesByNameResponse = new AddVegetablesByNameResponse();

        if (dealer != null) {
            Vegetables vegetables = vegetablesRepository.findByName(addVegetablesByNameRequest.getVegetableName());
            DealerVegetables dealerVegetables = dealerVegetablesMapper.mapToDealerVegetables(addVegetablesByNameRequest);

            if (vegetables != null) {
                dealerVegetables = dealerVegetablesRepository.findByDealerEmailAndVegetablesName(addVegetablesByNameRequest.getDealerEmail(), addVegetablesByNameRequest.getVegetableName());
                dealerVegetables.setPrice(addVegetablesByNameRequest.getVegetablePrice());
            } else {
                vegetables = dealerVegetablesMapper.mapToVegetables(addVegetablesByNameRequest);
                try {
                    vegetables = vegetablesRepository.save(vegetables);
                } catch (DataIntegrityViolationException ex) {
                    log.error("Exception occurred while saving a vegetable {}", ex.getMessage());
                    addVegetablesByNameResponse.setStatus(false);
                    addVegetablesByNameResponse.setResponse("Vegetable already exists");
                }
                dealerVegetables.setDealer(dealer);
                dealerVegetables.setVegetables(vegetables);
            }

            try {
                dealerVegetablesRepository.save(dealerVegetables);
                addVegetablesByNameResponse.setStatus(true);
                addVegetablesByNameResponse.setResponse("Vegetable successfully added");
            } catch (Exception ex) {
                log.error("Exception occurred while saving dealer and vegetable information {}", ex.getMessage());
                addVegetablesByNameResponse.setStatus(false);
                addVegetablesByNameResponse.setResponse("Something went wrong. Please try again after some time.");
            }
        } else {
            log.error("Dealer not found with {}", addVegetablesByNameRequest.getDealerEmail());
            addVegetablesByNameResponse.setStatus(false);
            addVegetablesByNameResponse.setResponse("Dealer not found");
        }

        log.info("Sending response to add vegetables by name request with {}", addVegetablesByNameResponse);
        return ResponseEntity.ok(addVegetablesByNameResponse);
    }

    public ResponseEntity<?> addVegetablesById(AddVegetablesByIdRequest addVegetablesByIdRequest) {
        log.info("Add vegetables by id request with data {}", addVegetablesByIdRequest);
        Optional<Dealer> optionalDealer = dealerRepository.findById(addVegetablesByIdRequest.getDealerId());
        AddVegetablesByIdResponse addVegetablesByIdResponse = new AddVegetablesByIdResponse();

        if (optionalDealer.isPresent()) {
            optionalDealer.ifPresent(dealer -> {
                Optional<Vegetables> optionalVegetables = vegetablesRepository.findById(addVegetablesByIdRequest.getVegetableId());

                if (optionalVegetables.isPresent()) {
                    optionalVegetables.ifPresent(vegetables -> {

                        DealerVegetables dealerVegetables = dealerVegetablesRepository.findByDealerIdAndVegetablesId(addVegetablesByIdRequest.getDealerId(), addVegetablesByIdRequest.getVegetableId());

                        if (dealerVegetables != null) {
                            dealerVegetables.setPrice(addVegetablesByIdRequest.getVegetablePrice());

                            try {
                                dealerVegetablesRepository.save(dealerVegetables);
                                addVegetablesByIdResponse.setStatus(true);
                                addVegetablesByIdResponse.setResponse("Vegetable successfully updated");
                            } catch (Exception ex) {
                                log.error("Exception occurred while updating dealer and vegetable information {}", ex.getMessage());
                                addVegetablesByIdResponse.setStatus(false);
                                addVegetablesByIdResponse.setResponse("Something went wrong. Please try again after some time.");
                            }
                        }
                        else {
                            dealerVegetables = dealerVegetablesMapper.mapToDealerVegetables(addVegetablesByIdRequest);
                            dealerVegetables.setDealer(dealer);
                            dealerVegetables.setVegetables(vegetables);

                            try {
                                dealerVegetablesRepository.save(dealerVegetables);
                                addVegetablesByIdResponse.setStatus(true);
                                addVegetablesByIdResponse.setResponse("Vegetable successfully added");
                            } catch (Exception ex) {
                                log.error("Exception occurred while saving dealer and vegetable information {}", ex.getMessage());
                                addVegetablesByIdResponse.setStatus(false);
                                addVegetablesByIdResponse.setResponse("Something went wrong. Please try again after some time.");
                            }
                        }
                    });
                } else {
                    log.error("Vegetable not found with {}", addVegetablesByIdRequest.getDealerId());
                    addVegetablesByIdResponse.setStatus(false);
                    addVegetablesByIdResponse.setResponse("Vegetable not found");
                }
            });
        } else {
            log.error("Dealer not found with {}", addVegetablesByIdRequest.getDealerId());
            addVegetablesByIdResponse.setStatus(false);
            addVegetablesByIdResponse.setResponse("Dealer not found");
        }

        log.info("Sending response to add vegetable by id request with {}", addVegetablesByIdResponse);
        return ResponseEntity.ok(addVegetablesByIdResponse);
    }
}
