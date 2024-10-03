package com.example.finally2.repository;

import com.example.finally2.entity.Product;
import com.example.finally2.util.status.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product , Long> {

    @Query(value = "SELECT DISTINCT pro FROM Product pro " +
            "LEFT JOIN FETCH pro.productCategories procate " +
            "LEFT JOIN FETCH procate.id.category cte " +
            "WHERE pro.status = :status " +
            "AND (:code IS NULL OR LOWER(pro.productCode) LIKE LOWER(:code) ESCAPE '\\') " +
            "AND (:name IS NULL OR LOWER(pro.productName) LIKE LOWER(:name) ESCAPE '\\') " +
            "AND (:startCreate IS NULL OR pro.createAt >= :startCreate) " +
            "AND (:endCreate IS NULL OR pro.createAt <= :endCreate) " +
            "AND (:categoryId IS NULL  OR (EXISTS (" +
            "SELECT 1 FROM ProductCategory pc2 " +
            "JOIN pc2.id.category c2 " +
            "WHERE pc2.id.product = pro AND pc2.status = 'ACTIVE' AND c2.status ='ACTIVE'" +
            "AND c2.id = :categoryId) " +
            "and procate.status = 'ACTIVE' AND cte.status = 'ACTIVE'))")


    Page<Product> finAllProduct(
            @Param("status") ProductStatus status,
            @Param("code") String productCode,
            @Param("name") String productName,
            @Param("startCreate") LocalDate startCreate,
            @Param("endCreate") LocalDate endCreate,
            @Param("categoryId") Long id,
            Pageable pageable);

    Boolean existsByProductCodeAndStatus(String productCode ,ProductStatus status);

    @Query(value = "select pro from Product pro left join fetch pro.productCategories procate left join fetch procate.id.category cte where pro.id=?1 and pro.status = 'ACTIVE'")
    Optional<Product> getProductById(Long id);
}
