package com.example.finally2.controller;

import com.example.finally2.constraint.groupvalidator.Create;
import com.example.finally2.constraint.groupvalidator.Update;
import com.example.finally2.dto.categorydto.request.CategoryRequest;
import com.example.finally2.dto.categorydto.response.CategoryResponse;
import com.example.finally2.dto.categorydto.response.CategoryResponseWithProducts;
import com.example.finally2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public Page<CategoryResponseWithProducts> getAll(@PageableDefault(page = 0 , size = 10) Pageable pageable,
                                                     @RequestParam(required = false) String categoryCode,
                                                     @RequestParam(required = false) String categoryName,
                                                     @RequestParam(required = false) LocalDate startCreate,
                                                     @RequestParam(required = false) LocalDate endCreate
                                                     ){
        return categoryService.getCategorys( categoryCode , categoryName , startCreate , endCreate, pageable);
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
    public ResponseEntity<Resource> exportData() {
        try {
            String fileName = "Records.xlsx"; // Tên file xuất ra

            // Xuất dữ liệu ra file
            categoryService.exportCategorysToExecl(fileName);

            // Tạo file resource
            File file = new File(System.getProperty("user.home") + File.separator + fileName);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(resource);
        } catch (IOException | IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
