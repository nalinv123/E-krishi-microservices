package com.nvdevelopers.e_krishi.request.dealer;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class RegisterRequest implements Serializable {

    @NonNull
    @NotEmpty
    private String name;

    @NonNull
    @NotEmpty
    @Email(message = "Email should be valid")
    private String email;

    @NonNull
    @NotEmpty
    private String password;

    @NonNull
    @NotEmpty
    private String city;

    @NonNull
    @NotEmpty
    private String mobile;
}
