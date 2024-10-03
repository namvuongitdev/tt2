package com.example.finally2.controller;

import com.example.finally2.constraint.groupvalidator.Create;
import com.example.finally2.constraint.groupvalidator.Update;
import com.example.finally2.dto.categorydto.request.CategoryRequest;
import com.example.finally2.dto.categorydto.response.CategoryResponse;
import com.example.finally2.dto.categorydto.response.CategoryResponseWithProducts;
import com.example.finally2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public Page<CategoryResponseWithProducts> getAll(@PageableDefault(page = 0 , size = 10) @SortDefault(sort = "createAt" ,direction = Sort.Direction.DESC)
                                                     Pageable pageable,
                                                     @RequestParam(required = false) String categoryCode,
                                                     @RequestParam(required = false) String categoryName,
                                                     @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startCreate,
                                                     @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endCreate
                                                     ){
        return categoryService.getCategorys( categoryCode , categoryName , startCreate , endCreate, pageable);
    }

    @GetMapping("/no-product")
    public List<CategoryResponse> getAllCategoryNoProduct(){
        return categoryService.getCategorysNoProduct();
    }

    @PostMapping("/add")
    public CategoryResponse add(@Validated(Create.class) @ModelAttribute CategoryRequest request){
       return categoryService.addCategory(request);
    }

    @PutMapping("/update")
    public CategoryResponse update(@Validated(Update.class) @ModelAttribute CategoryRequest request , @RequestParam Long id){
        return categoryService.updateCategory(id, request);
    }

    @GetMapping("/detail")
    public CategoryResponse detail(@RequestParam Long id){
        return categoryService.detailCategory(id);
    }

    @DeleteMapping("/delete")
    public CategoryResponse delete(@RequestParam Long id){
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel(
            @RequestParam(required = false) String categoryCode,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startCreate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endCreate
    ) {
        try {
            ByteArrayOutputStream outputStream = categoryService.exportCategoryToExecl(categoryCode , categoryName , startCreate , endCreate);
            byte[] bytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=data.xlsx");

            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
