package com.example.finally2.mapper.productmapper.request;

import com.example.finally2.dto.productdto.request.ProductRequest;
import com.example.finally2.entity.Product;
import com.example.finally2.mapper.MapperBase;
import com.example.finally2.util.data.ValueDefault;
import com.example.finally2.util.date.DateUtil;
import com.example.finally2.util.status.ProductStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring" , imports = {ProductStatus.class , DateUtil.class , ValueDefault.class})
public interface ProductMapperReqeust extends MapperBase<Product , ProductRequest> {

    @Override
    @Mapping(target = "status" , expression = "java(ProductStatus.ACTIVE)")
    @Mapping(target = "createAt" , expression = "java(DateUtil.getCurrentDate())")
    @Mapping(target = "modifiedDate" , expression = "java(DateUtil.getCurrentDate())")
    @Mapping(target = "modifiedBy" , expression = "java(ValueDefault.modifiedBy)")
    @Mapping(target = "createBy" , expression = "java(ValueDefault.modifiedBy)")
    Product toEntity(ProductRequest request);

    @Mapping(target = "modifiedDate" , expression = "java(DateUtil.getCurrentDate())")
    @Mapping(target = "modifiedBy" , expression = "java(ValueDefault.modifiedBy)")
    @Mapping(target = "productCode" , ignore = true)
    Product updateToEntity(ProductRequest request , @MappingTarget Product product);


}
