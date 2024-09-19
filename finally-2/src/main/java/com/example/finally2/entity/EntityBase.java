package com.example.finally2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class EntityBase {

    @Column(name = "create_at" , updatable = false)
    private LocalDate createAt;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "create_by")
    private String createBy;


}
