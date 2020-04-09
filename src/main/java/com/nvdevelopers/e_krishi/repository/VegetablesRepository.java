package com.nvdevelopers.e_krishi.repository;

import com.nvdevelopers.e_krishi.model.Vegetables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VegetablesRepository extends JpaRepository<Vegetables, Long> {

    Vegetables findByName(String name);
}
