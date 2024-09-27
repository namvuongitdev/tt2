package com.example.finally2.dto.categorydto.request;

import com.example.finally2.constraint.costomvalidator.category.unique.UniqueCategoryCode;
import com.example.finally2.constraint.groupvalidator.Create;
import com.example.finally2.constraint.groupvalidator.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    private Long id;

    @NotBlank(message = "NotBlank" , groups = {Create.class})
    @Length(min = 3 , max = 50 , message = "Length" , groups = {Create.class})
    @UniqueCategoryCode( message = "UniqueCategoryCode" , groups = {Create.class})
    private String categoryCode;

    @NotBlank(message = "NotBlank" , groups = {Create.class , Update.class})
    @Length(min = 3 , max = 50 , message = "Length" , groups = {Create.class , Update.class})
    private String categoryName;

    @NotBlank(message = "NotBlank" , groups = {Create.class , Update.class})
    @Length(min = 3 , max = 50 , message = "Length" , groups = {Create.class ,Update.class})
    private String description;

//    @NotNull(message = "NotNull" , groups = Create.class)
    private MultipartFile file;

}
