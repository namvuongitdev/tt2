package com.example.finally2.entity;

import com.example.finally2.util.status.CategoryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Category extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    @OneToMany(mappedBy = "id.category")
    private List<ProductCategory> productCategories = new ArrayList<>();

    @Transient
    private Boolean isCheck = true;
}
