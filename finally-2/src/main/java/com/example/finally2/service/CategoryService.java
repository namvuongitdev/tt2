package com.example.finally2.service;

import com.example.finally2.dto.categorydto.request.CategoryRequest;
import com.example.finally2.dto.categorydto.response.CategoryResponse;
import com.example.finally2.dto.categorydto.response.CategoryResponseWithProducts;
import com.example.finally2.entity.Category;
import com.example.finally2.execption.custom.ListIsEmptyExecption;
import com.example.finally2.execption.custom.NotFoundExecption;
import com.example.finally2.mapper.CategoryMapper.request.CategoryMapperRequest;
import com.example.finally2.mapper.CategoryMapper.response.CategoryMapperResponse;
import com.example.finally2.mapper.CategoryMapper.response.CategoryMapperResponseWithPros;
import com.example.finally2.mapper.CategoryMapper.response.CategoryResponseMapperExecl;
import com.example.finally2.repository.CategoryRepository;
import com.example.finally2.util.data.StringUtils;
import com.example.finally2.util.data.ValueDefault;
import com.example.finally2.util.file.ExportExecl;
import com.example.finally2.util.file.UploadImage;
import com.example.finally2.util.status.CategoryStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapperRequest categoryMapperRequest;

    @Autowired
    private CategoryMapperResponse categoryMapperResponse;

    @Autowired
    private UploadImage uploadImage;

    @Autowired
    private ExportExecl exportExecl;

    @Autowired
    private CategoryMapperResponseWithPros categoryMapperResponseWithPros;

    @Autowired
    private CategoryResponseMapperExecl categoryResponseMapperExecl;

    public Page<CategoryResponseWithProducts> getCategorys(String categoryCode, String categoryName, LocalDate startCreate, LocalDate endCreate, Pageable pageable) {
        Page<Category> categories = categoryRepository.findAllCategory(
                CategoryStatus.ACTIVE,
                StringUtils.replace(categoryCode),
                StringUtils.replace(categoryName),
                startCreate,
                endCreate,
                pageable);
        Page<CategoryResponseWithProducts> categoryPage = categories.map(categoryMapperResponseWithPros::toDTO);
        return categoryPage;
    }

    public List<CategoryResponse> getCategorysNoProduct() {
        return categoryMapperResponse.listToDTO(categoryRepository.findAllByStatus(CategoryStatus.ACTIVE));
    }

    @Transactional
    public CategoryResponse addCategory(CategoryRequest request) {
        Category category = categoryMapperRequest.toEntity(request);
        if (request.getFile() != null) {
            category.setImage(uploadImage.upload(request.getFile()));
        }
        return categoryMapperResponse.toDTO(categoryRepository.save(category));
    }

    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository.getCategoryById(id).orElseThrow(() -> new NotFoundExecption("NotFound"));
        if (request.getFile() != null) {
            category.setImage(uploadImage.upload(request.getFile()));
        }else{
            if(request.isRemoveImage()){
                category.setImage(null);
            }
        }
        Category newCategory = categoryMapperRequest.updateToEntity(request, category);
        return categoryMapperResponse.toDTO(categoryRepository.save(newCategory));
    }

    public CategoryResponse detailCategory(Long id) {
        Category category = categoryRepository.getCategoryById(id).orElseThrow(() -> new NotFoundExecption("NotFound"));
        return categoryMapperResponse.toDTO(category);
    }

    @Transactional
    public CategoryResponse deleteCategory(Long id) {
        Category category = categoryRepository.getCategoryById(id).orElseThrow(() -> new NotFoundExecption("NotFound"));
        category.setStatus(CategoryStatus.STOPWORKING);
        return categoryMapperResponse.toDTO(category);
    }


    public ByteArrayOutputStream exportCategoryToExecl(String categoryCode, String categoryName, LocalDate startCreate, LocalDate endCreate) throws IOException {
        Page<Category> categories =   categoryRepository.findAllCategory(
                CategoryStatus.ACTIVE,
                StringUtils.replace(categoryCode),
                StringUtils.replace(categoryName),
                startCreate,
                endCreate,
                null);
        if(categories.getTotalElements() == 0){
            throw new ListIsEmptyExecption("errorListExecl");
        }
        return exportExecl.exportToExcelCategory(categoryResponseMapperExecl.listToDTO(categories.getContent()), ValueDefault.TITLE_CATEGORY);
    }
}
