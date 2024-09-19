package com.example.finally2.dto.productcategorydto.response;

import com.example.finally2.dto.categorydto.response.CategoryResponse;
import com.example.finally2.dto.productdto.response.ProductResponse;
import com.example.finally2.util.status.ProductCategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryResponse {

    private ProductResponse productResponse;
    private CategoryResponse categoryResponse;
    private ProductCategoryStatus status;
    private LocalDate createAt;
    private LocalDate modifiedDate;
    private String modifiedBy;

}
