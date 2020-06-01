package com.nvdevelopers.e_krishi.request.farmer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SearchVegetableRequest implements Serializable {

    private String name;

    private String city;
}
