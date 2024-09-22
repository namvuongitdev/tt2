package com.example.finally2.mapper.productmapper.response;

import com.example.finally2.dto.productdto.response.ProductResponse;
import com.example.finally2.dto.productdto.response.ProductResponseExecl;
import com.example.finally2.mapper.MapperBase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapperReponseExecl extends MapperBase<ProductResponse , ProductResponseExecl> {
}
