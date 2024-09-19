package com.example.finally2.mapper.productmapper.response;

import com.example.finally2.dto.productdto.response.ProductResponseToCategory;
import com.example.finally2.entity.Product;
import com.example.finally2.mapper.MapperBase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdudctMapperResponseToCategory extends MapperBase<Product , ProductResponseToCategory> {
}
