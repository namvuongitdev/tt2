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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
                                        @RequestParam(required = false) Long category,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startCreate,
                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endCreate){

        return productService.getProducts(productCode , productName , startCreate , endCreate ,category , pageable);
    }

    @PostMapping(value = "/add")
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

    @GetMapping("/count")
    public Integer count(){
        return productService.countProduct();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel() {
        try {
            ByteArrayOutputStream outputStream = productService.exportProductToExecl();
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
