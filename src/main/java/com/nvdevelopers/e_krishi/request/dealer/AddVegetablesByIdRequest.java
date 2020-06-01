package com.nvdevelopers.e_krishi.request.dealer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class AddVegetablesByIdRequest implements Serializable {

    @NonNull
    @Positive
    private Long dealerId;

    @NonNull
    @Positive
    private Long vegetableId;

    @NonNull
    @NotEmpty
    private String vegetablePrice;
}
