package com.nvdevelopers.e_krishi.repository;

import com.nvdevelopers.e_krishi.model.DealerVegetables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealerVegetablesRepository extends JpaRepository<DealerVegetables, Long> {

    List<DealerVegetables> findByVegetablesNameAndDealerCity(String vegetable, String city);

    DealerVegetables findByDealerIdAndVegetablesId(Long dealerId, Long vegetableId);
    /*
    SELECT d.email, v.name, dv.price FROM dealer_info as d INNER JOIN dealer_vegetables_info dv on d.dealer_id = dv.dealer_id INNER JOIN vegetables_info v ON v.vegetable_id = dv.vegetable_id WHERE d.city = "pune" AND v.name = "tomato"
     */

    List<DealerVegetables> findByDealerEmail(String dealerEmail);

    DealerVegetables findByDealerEmailAndVegetablesName(String dealerEmail, String vegetablesName);
}
