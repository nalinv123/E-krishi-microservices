package com.nvdevelopers.e_krishi.mapper;

import com.nvdevelopers.e_krishi.model.DealerVegetables;
import com.nvdevelopers.e_krishi.model.Vegetables;
import com.nvdevelopers.e_krishi.request.dealer.AddVegetablesByIdRequest;
import com.nvdevelopers.e_krishi.request.dealer.AddVegetablesByNameRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DealerVegetablesMapper {

    @Mappings(
            {
                    @Mapping(source = "addVegetablesByNameRequest.vegetableName", target = "name")
            }
    )
    Vegetables mapToVegetables(AddVegetablesByNameRequest addVegetablesByNameRequest);

    @Mappings(
            {
                    @Mapping(source = "addVegetablesByNameRequest.vegetablePrice", target = "price")
            }
    )
    DealerVegetables mapToDealerVegetables(AddVegetablesByNameRequest addVegetablesByNameRequest);

    @Mappings(
            {
                    @Mapping(source = "addVegetablesByIdRequest.vegetablePrice", target = "price")
            }
    )
    DealerVegetables mapToDealerVegetables(AddVegetablesByIdRequest addVegetablesByIdRequest);
}
