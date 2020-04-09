package com.nvdevelopers.e_krishi.response.dealer;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterResponse implements Serializable {

    private boolean status;

    private String response;
}
