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

    @Query(value = "select pro from Product pro left join fetch pro.productCategories procate" +
            " left join fetch procate.id.category cte where pro.status = :status and procate.status = 'ACTIVE'" +
            " and (:code is null or pro.productCode = :code)" +
            " and (:name is null or pro.productName = :name) and" +
            "(:startCreate is null or pro.createAt >= :startCreate) and" +
            "(:endCreate is null or pro.createAt <= :endCreate) and" +
            "(:categoryId is null or cte.id = :categoryId)"
    )
    Page<Product> finAllProduct(
            @Param("status") ProductStatus status,
            @Param("code") String categoryCode,
            @Param("name") String categoryName,
            @Param("startCreate") LocalDate startCreate,
            @Param("endCreate") LocalDate endCreate,
            @Param("categoryId") Long id,
            Pageable pageable);

    Boolean existsByProductCode(String productCode);

    Integer countAllByStatus(ProductStatus status);

    @Query(value = "select pro from Product pro left join fetch pro.productCategories procate left join fetch procate.id.category cte where pro.id=?1 and procate.status = 'ACTIVE'")
    Optional<Product> getProductById(Long id);
}
