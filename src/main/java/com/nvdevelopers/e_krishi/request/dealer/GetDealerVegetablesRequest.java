package com.nvdevelopers.e_krishi.request.dealer;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetDealerVegetablesRequest implements Serializable {

    private String email;
}
