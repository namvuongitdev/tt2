package com.example.finally2.repository;

import com.example.finally2.entity.Category;
import com.example.finally2.util.status.CategoryStatus;
import com.example.finally2.util.status.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select distinct cte from Category cte left join fetch cte.productCategories procate " +
            "left join fetch procate.id.product pro where cte.status = :status and" +
            "(:code is null or LOWER(cte.categoryCode) LIKE LOWER(CONCAT('%', :code, '%'))) and" +
            "(:name is null or LOWER(cte.categoryName) LIKE LOWER(CONCAT('%', :name, '%'))) and" +
            "(:startCreate is null or cte.createAt >= :startCreate) and" +
            "(:endCreate is null or cte.createAt <= :endCreate)"
    )
    Page<Category> findAllCategory(@Param("status") CategoryStatus status,
                                   @Param("code") String categoryCode,
                                   @Param("name") String categoryName,
                                   @Param("startCreate")LocalDate startCreate,
                                   @Param("endCreate") LocalDate endCreate,
                                   Pageable pageable);

    boolean existsByCategoryCode(String categoryCode);

    Integer countAllByStatus(CategoryStatus status);

    List<Category> findAllByStatus(CategoryStatus status);
}
