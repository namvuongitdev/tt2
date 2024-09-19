package com.example.finally2.dto.productdto.response;

import com.example.finally2.dto.categorydto.response.CategoryResponse;
import com.example.finally2.dto.productcategorydto.response.ProductCategoryResponse;
import com.example.finally2.util.status.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse{

    private Long id;
    private String productName;
    private String productCode;
    private String description;
    private Double price;
    private Long quantity;
    private String image;
    private LocalDate createAt;
    private LocalDate modifiedDate;
    private String modifiedBy;
    private String createBy;
    private ProductStatus status;

    @JsonIgnore
    private List<ProductCategoryResponse> productCategoryResponses = new ArrayList<>();

    private List<CategoryResponse> categoryResponses = new ArrayList<>();

    public List<CategoryResponse> getCategoryResponses() {
        List<CategoryResponse> categoryResponsesNew = new ArrayList<>();
        if(!this.productCategoryResponses.isEmpty()){
            this.productCategoryResponses.forEach(productCategoryResponse -> {
                categoryResponsesNew.add(productCategoryResponse.getCategoryResponse());
            });
            return categoryResponsesNew;
        }
        return categoryResponses;
    }
}
