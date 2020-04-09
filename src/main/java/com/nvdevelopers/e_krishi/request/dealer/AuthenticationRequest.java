package com.nvdevelopers.e_krishi.request.dealer;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class AuthenticationRequest implements Serializable {

    @NonNull
    @NotEmpty
    private String email;

    @NonNull
    @NotEmpty
    private String password;
}
