package com.nvdevelopers.e_krishi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vegetables_info")
@Data
public class Vegetables {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vegetable_id")
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(max = 25)
    private String name;
}
