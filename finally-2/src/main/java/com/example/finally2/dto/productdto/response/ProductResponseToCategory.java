package com.example.finally2.dto.productdto.response;

import com.example.finally2.util.status.ProductStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseToCategory {

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
}
