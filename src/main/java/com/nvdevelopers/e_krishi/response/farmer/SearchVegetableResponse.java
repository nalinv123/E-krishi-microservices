package com.nvdevelopers.e_krishi.response.farmer;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SearchVegetableResponse implements Serializable {

//    private List<Dealers> dealers;
//
//    public static class Dealers {
//
//        private String name;
//
//        private String email;
//
//        private String mobile;
//    }

    private List<DealerVegetables> dealerVegetables;

    @Data
    public static class DealerVegetables {

        private Dealer dealer;

        private Vegetables vegetables;

        private String price;

        @Data
        public static class Dealer {

            private String name;

            private String email;

            private String mobile;
        }

        @Data
        public static class Vegetables {

            private String name;
        }
    }
}
