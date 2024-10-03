package com.example.finally2.dto.productdto.request;

import com.example.finally2.constraint.costomvalidator.product.unique.UniqueProductCode;
import com.example.finally2.constraint.groupvalidator.Create;
import com.example.finally2.constraint.groupvalidator.Update;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {

    private Long id;

    @NotBlank(message = "NotBlank" , groups = {Create.class , Update.class})
    @Length(min = 3 , max = 250 , message = "Length" , groups = {Create.class , Update.class})
    private String productName;

    @NotBlank(message = "NotBlank" , groups = {Create.class})
    @Length(min = 3 , max = 50 , message = "Length" , groups = {Create.class})
    @UniqueProductCode(message = "UniqueProductCode" , groups = {Create.class})
    private String productCode;

    @NotBlank(message = "NotBlank" , groups = {Create.class , Update.class})
    @Length(min = 3 , max = 250 , message = "Length" , groups = {Create.class , Update.class})
    private String description;

    @NotNull(message = "NotNull" , groups = {Create.class , Update.class})
    @Positive(message = "Positive" , groups = {Create.class , Update.class})
//    @Max(value = 2000000000, message = "Max", groups = {Create.class, Update.class})
    private Integer price;

    @NotNull(message = "NotNull" , groups = {Create.class , Update.class})
    @Positive(message = "Positive" , groups = {Create.class , Update.class})
    private Long quantity;

    private MultipartFile file;

    private boolean removeImage = false;

    private List<Long> categorys = new ArrayList<>();
}
