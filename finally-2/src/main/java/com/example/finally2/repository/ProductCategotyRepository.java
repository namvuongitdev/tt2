package com.example.finally2.repository;

import com.example.finally2.entity.CompositeKey;
import com.example.finally2.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductCategotyRepository extends JpaRepository<ProductCategory , CompositeKey> {

}
