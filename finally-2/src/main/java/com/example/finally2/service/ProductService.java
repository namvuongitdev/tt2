package com.example.finally2.service;

import com.example.finally2.dto.productdto.request.ProductRequest;
import com.example.finally2.dto.productdto.response.ProductResponse;
import com.example.finally2.dto.productdto.response.ProductResponseExecl;
import com.example.finally2.entity.Category;
import com.example.finally2.entity.CompositeKey;
import com.example.finally2.entity.Product;
import com.example.finally2.entity.ProductCategory;
import com.example.finally2.execption.custom.NotFoundExecption;
import com.example.finally2.mapper.productmapper.request.ProductMapperReqeust;
import com.example.finally2.mapper.productmapper.response.ProductMapperReponseExecl;
import com.example.finally2.mapper.productmapper.response.ProductMapperResponse;
import com.example.finally2.repository.CategoryRepository;
import com.example.finally2.repository.ProductRepository;
import com.example.finally2.util.data.ValueDefault;
import com.example.finally2.util.date.DateUtil;
import com.example.finally2.util.file.ExportExecl;
import com.example.finally2.util.file.UploadImage;
import com.example.finally2.util.status.ProductCategoryStatus;
import com.example.finally2.util.status.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapperResponse productMapperResponse;

    @Autowired
    private ProductMapperReqeust productMapperReqeust;

    @Autowired
    private UploadImage uploadImage;

    @Autowired
    private ExportExecl exportExecl;

    @Autowired
    private ProductMapperReponseExecl productMapperReponseExecl;

    @Autowired
    private CategoryRepository categoryRepository;

    private List<ProductResponseExecl> productResponseExeclArrayList = new ArrayList<>();

    public Page<ProductResponse> getProducts(String productCode, String productName, LocalDate startCreate, LocalDate endCreate, Long categoryId, Pageable pageable) {
        Page<Product> products = productRepository.finAllProduct(ProductStatus.ACTIVE,
                productCode,
                productName,
                startCreate,
                endCreate,
                categoryId,
                pageable);
        Page<ProductResponse> productResponses = products.map(productMapperResponse::toDTO);
        productResponseExeclArrayList = productMapperReponseExecl.listToDTO(productResponses.getContent());
        return productResponses;
    }

    @Transactional
    public ProductResponse addProduct(ProductRequest request) {
        List<Category> categories = categoryRepository.findAllById(request.getCategorys());

        String url = uploadImage.upload(request.getFile());
        Product product = productMapperReqeust.toEntity(request);
        product.setImage(url);
        for (int i = 0; i < categories.size(); i++) {
            ProductCategory productCategory = new ProductCategory();
            this.newProductCategory(productCategory, new CompositeKey(product, categories.get(i)), ProductCategoryStatus.ACTIVE, true);
            product.getProductCategories().add(i, productCategory);
        }
        return productMapperResponse.toDTO(productRepository.save(product));
    }

    @Transactional
    public ProductResponse deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundExecption("NotFound"));
        product.setStatus(ProductStatus.STOPWORKING);
        return productMapperResponse.toDTO(productRepository.save(product));
    }

    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundExecption("NotFound"));
        List<Category> categories = categoryRepository.findAllById(request.getCategorys());

        Product productNew = productMapperReqeust.updateToEntity(request, product);
        if (request.getFile() != null) {
            productNew.setImage(uploadImage.upload(request.getFile()));
        } else {
            productNew.setImage(product.getImage());
        }
        Boolean isCheckCategoryExtis = true;

        for (int i = 0; i < categories.size(); i++) {
            for (int j = 0; j < product.getProductCategories().size(); j++) {
                Category category = product.getProductCategories().get(j).getId().getCategory();
                if (categories.get(i).getId() == category.getId()) {
                    category.setIsCheck(false);
                    isCheckCategoryExtis = false;
                    if (product.getProductCategories().get(j).getStatus().equals(ProductCategoryStatus.STOPWORKING)) {
                        this.newProductCategory(product.getProductCategories().get(j), product.getProductCategories().get(j).getId(), ProductCategoryStatus.ACTIVE, false);
                    }
                    break;
                } else {
                    isCheckCategoryExtis = true;
                }
                if (j == product.getProductCategories().size() - 1 && isCheckCategoryExtis) {
                    ProductCategory productCategory = new ProductCategory();
                    this.newProductCategory(productCategory, new CompositeKey(product, categories.get(i)), ProductCategoryStatus.ACTIVE, true);
                    product.getProductCategories().add(productCategory);
                }
            }
        }

        product.getProductCategories().forEach(o -> {
            if (o.getId().getCategory().getIsCheck()) {
                this.newProductCategory(o, o.getId(), ProductCategoryStatus.STOPWORKING, false);
            }
        });

        return productMapperResponse.toDTO(productRepository.save(productNew));
    }

    public ProductResponse detailProduct(Long id) {
        Product product = productRepository.getProductById(id).orElseThrow(() -> new NotFoundExecption("NotFound"));
        return productMapperResponse.toDTO(product);
    }

    public void newProductCategory(ProductCategory productCategory, CompositeKey id, ProductCategoryStatus status, Boolean isCreate) {
        if (isCreate) {
            productCategory.setId(id);
            productCategory.setCreateAt(DateUtil.getCurrentDate());
        }
        productCategory.setStatus(status);
        productCategory.setModifiedDate(DateUtil.getCurrentDate());
        productCategory.setModifiedBy(ValueDefault.modifiedBy);
        productCategory.setCreateBy(ValueDefault.modifiedBy);
    }

    public Integer countProduct() {
        return productRepository.countAllByStatus(ProductStatus.ACTIVE);
    }


    public ByteArrayOutputStream exportProductToExecl() throws IOException {
        return exportExecl.exportToExcel(productResponseExeclArrayList);
    }

}
