package com.nvdevelopers.e_krishi.request.farmer;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchVegetableRequest implements Serializable {

    private String name;

    private String city;
}
