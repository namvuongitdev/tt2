package com.example.finally2.dto.categorydto.response;

import com.example.finally2.dto.productcategorydto.response.ProductCategoryResponse;
import com.example.finally2.dto.productdto.response.ProductResponse;
import com.example.finally2.dto.productdto.response.ProductResponseToCategory;
import com.example.finally2.util.file.UploadImage;
import com.example.finally2.util.status.CategoryStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseWithProducts {

    private Long id;
    private String categoryName;
    private String categoryCode;
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createAt;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;
    private String modifiedBy;
    private String createBy;
    private String image;
    private CategoryStatus status;

    @JsonIgnore
    private List<ProductCategoryResponse> productCategoryResponses = new ArrayList<>();

    private List<ProductResponseToCategory> products = new ArrayList<>();

    public List<ProductResponseToCategory> getProducts() {
        if(!this.productCategoryResponses.isEmpty()){
            this.productCategoryResponses.forEach(productCategoryResponse -> {
                ProductResponse productResponse = productCategoryResponse.getProductResponse();

                products.add(ProductResponseToCategory.builder()
                        .id(productResponse.getId())
                        .productCode(productResponse.getProductCode())
                        .productName(productResponse.getProductName())
                        .description(productResponse.getDescription())
                        .price(productResponse.getPrice())
                        .quantity(productResponse.getQuantity())
                        .image(productResponse.getImage())
                        .createAt(productResponse.getCreateAt())
                        .modifiedDate(productResponse.getModifiedDate())
                        .modifiedBy(productResponse.getModifiedBy())
                        .createBy(productResponse.getCreateBy())
                        .status(productResponse.getStatus())
                        .build()

                );
            });
            return products;
        }
        return products;
    }

    public String getImage(){
       return UploadImage.converToBase64(image);
    }
}
