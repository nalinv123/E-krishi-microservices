package com.nvdevelopers.e_krishi.request.dealer;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class AddVegetablesByNameRequest implements Serializable {

    @NonNull
    @NotEmpty
    private String dealerEmail;

    @NonNull
    @NotEmpty
    @Size(max = 25)
    private String vegetableName;

    @NonNull
    @NotEmpty
    private String vegetablePrice;
}
