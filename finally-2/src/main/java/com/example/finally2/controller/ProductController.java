package com.example.finally2.controller;

import com.example.finally2.constraint.groupvalidator.Create;
import com.example.finally2.constraint.groupvalidator.Update;
import com.example.finally2.dto.productdto.request.ProductRequest;
import com.example.finally2.dto.productdto.response.ProductResponse;
import com.example.finally2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public Page<ProductResponse> getAll(@PageableDefault(page = 0 , size = 10) Pageable pageable,
                                        @RequestParam(required = false) String productCode,
                                        @RequestParam(required = false) String productName,
                                        @RequestParam(required = false) LocalDate startCreate,
                                        @RequestParam(required = false) LocalDate endCreate){
        return productService.getProducts(productCode , productName , startCreate , endCreate ,pageable);
    }

    @PostMapping("/add")
    public ProductResponse add(@Validated({Create.class}) @ModelAttribute ProductRequest request){
        return productService.addProduct(request);
    }

    @PutMapping("/update")
    public ProductResponse update(@Validated({Update.class}) @ModelAttribute ProductRequest request , @RequestParam Long id){
        return productService.updateProduct(id , request);
    }


    @DeleteMapping("/delete")
    public ProductResponse delete(@RequestParam Long id){
        return productService.deleteProduct(id);
    }

    @GetMapping("/detail")
    public ProductResponse detail(@RequestParam Long id){
        return productService.detailProduct(id);
    }
}
