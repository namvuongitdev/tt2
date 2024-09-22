package com.example.finally2.dto.categorydto.response;

import com.example.finally2.entity.Product;
import com.example.finally2.util.status.CategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private Long id;
    private String categoryName;
    private String categoryCode;
    private String description;
    private LocalDate createAt;
    private LocalDate modifiedDate;
    private String modifiedBy;
    private String image;
    private CategoryStatus status;

}
