package com.nvdevelopers.e_krishi.response.dealer;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetDealerVegetablesResponse implements Serializable {

    private List<DealerVegetables> dealerVegetables;

    @Data
    public static class DealerVegetables {

        private Vegetables vegetables;

        private String price;

        @Data
        public static class Vegetables {

            private String name;
        }
    }
}
