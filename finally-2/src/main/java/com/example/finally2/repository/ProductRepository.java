package com.example.finally2.repository;

import com.example.finally2.entity.Product;
import com.example.finally2.util.status.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

public interface ProductRepository extends JpaRepository<Product , Long> {

    @Query(value = "select pro from Product pro left join fetch pro.productCategories procate" +
            " left join fetch procate.id.category cte where pro.status = :status" +
            " and (:code is null or pro.productCode = :code)" +
            " and (:name is null or pro.productName = :name) and" +
            "(:startCreate is null or pro.createAt >= :startCreate) and" +
            "(:endCreate is null or pro.createAt <= :endCreate)"
    )
    Page<Product> finAllProduct(
            @Param("status") ProductStatus status,
            @Param("code") String categoryCode,
            @Param("name") String categoryName,
            @Param("startCreate") LocalDate startCreate,
            @Param("endCreate") LocalDate endCreate,
            Pageable pageable);

    Boolean existsByProductCode(String productCode);
}
