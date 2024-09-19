package com.example.finally2.entity;

import com.example.finally2.util.status.ProductCategoryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends EntityBase {

    @EmbeddedId
    private CompositeKey id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductCategoryStatus status;
}
