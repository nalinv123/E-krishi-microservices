package com.nvdevelopers.e_krishi.mapper;

import com.nvdevelopers.e_krishi.model.Dealer;
import com.nvdevelopers.e_krishi.model.DealerVegetables;
import com.nvdevelopers.e_krishi.model.Vegetables;
import com.nvdevelopers.e_krishi.response.farmer.SearchVegetableResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FarmerMapper {

    @Mappings(
            {
                    @Mapping(source = "dealer.name", target = "name"),
                    @Mapping(source = "dealer.email", target = "email"),
                    @Mapping(source = "dealer.mobile", target = "mobile"),
            }
    )
    SearchVegetableResponse.DealerVegetables.Dealer mapDealer(Dealer dealer);

    @Mappings(
            {
                    @Mapping(source = "vegetables.name", target = "name")
            }
    )
    SearchVegetableResponse.DealerVegetables.Vegetables mVegetables(Vegetables vegetables);

    SearchVegetableResponse.DealerVegetables mapToSearchVegetableResponse(DealerVegetables dealerVegetables);
}
