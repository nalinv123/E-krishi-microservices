package com.nvdevelopers.e_krishi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "dealer_vegetables_info")
@Data
public class DealerVegetables {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dealer_vegetable_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "dealer_id", nullable = false)
    private Dealer dealer;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "vegetable_id", nullable = false)
    private Vegetables vegetables;

    private String price;
}
