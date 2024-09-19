package com.example.finally2.mapper.productmapper.response;

import com.example.finally2.dto.productdto.response.ProductResponse;
import com.example.finally2.entity.Product;
import com.example.finally2.mapper.MapperBase;
import com.example.finally2.mapper.ProductCategoryMapper.response.ProductCategoryMapperResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring" , uses = {ProductCategoryMapperResponse.class})
public interface ProductMapperResponse extends MapperBase<Product , ProductResponse> {

    @Override
    @Mapping(target = "productCategoryResponses" , source = "productCategories")
    ProductResponse toDTO(Product product);

    @Override
    List<ProductResponse> listToDTO(List<Product> listE);
}
