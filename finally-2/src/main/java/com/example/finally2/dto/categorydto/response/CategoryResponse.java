package com.example.finally2.dto.categorydto.response;

import com.example.finally2.entity.Product;
import com.example.finally2.util.status.CategoryStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String categoryCode;
    private String categoryName;
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createAt;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    private String modifiedBy;
    private String createBy;
    private String image;
    private CategoryStatus status;

}
