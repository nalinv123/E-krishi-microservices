package com.nvdevelopers.e_krishi.request.dealer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class GetDealerVegetablesRequest implements Serializable {

    private String email;
}
