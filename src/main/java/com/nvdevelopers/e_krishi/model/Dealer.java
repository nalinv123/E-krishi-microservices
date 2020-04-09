package com.nvdevelopers.e_krishi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dealer_info")
@Data
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dealer_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    @Size(max = 25)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String mobile;
}
