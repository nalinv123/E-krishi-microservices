package com.nvdevelopers.e_krishi.mapper;

import com.nvdevelopers.e_krishi.model.Dealer;
import com.nvdevelopers.e_krishi.model.DealerVegetables;
import com.nvdevelopers.e_krishi.model.Vegetables;
import com.nvdevelopers.e_krishi.request.dealer.AddVegetablesByNameRequest;
import com.nvdevelopers.e_krishi.request.dealer.RegisterRequest;
import com.nvdevelopers.e_krishi.response.dealer.AuthenticationResponse;
import com.nvdevelopers.e_krishi.response.dealer.GetDealerVegetablesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DealerMapper {

    @Mappings(
            {
                    @Mapping(source = "registerRequest.name", target = "name"),
                    @Mapping(source = "registerRequest.email", target = "email"),
                    @Mapping(source = "registerRequest.city", target = "city"),
                    @Mapping(source = "registerRequest.mobile", target = "mobile")
            }
    )
    Dealer mapToDealer(RegisterRequest registerRequest);

    @Mappings(
            {
                    @Mapping(source = "dealer.id", target = "id"),
                    @Mapping(source = "dealer.name", target = "name"),
                    @Mapping(source = "dealer.email", target = "email"),
                    @Mapping(source = "dealer.city", target = "city"),
                    @Mapping(source = "dealer.mobile", target = "mobile"),
            }
    )
    AuthenticationResponse.Dealer mapToAuthenticationResponseDealer(Dealer dealer);

    GetDealerVegetablesResponse.DealerVegetables mapToGetDealerVegetablesResponse(DealerVegetables dealerVegetables);
}
