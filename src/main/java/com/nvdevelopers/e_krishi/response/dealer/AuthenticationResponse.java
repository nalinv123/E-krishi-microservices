package com.nvdevelopers.e_krishi.response.dealer;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private boolean status;

    private String response;

    private String token;

    private Dealer dealer;

    @Data
    public static class Dealer {

        private Long id;

        private String name;

        private String email;

        private String city;

        private String mobile;
    }
}
