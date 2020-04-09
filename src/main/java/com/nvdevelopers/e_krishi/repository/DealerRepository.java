package com.nvdevelopers.e_krishi.repository;

import com.nvdevelopers.e_krishi.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {

    Dealer findUserByEmail(String email);
}
