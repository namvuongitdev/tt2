package com.example.finally2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class EntityBase {

    @Column(name = "create_at" , updatable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate createAt;

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate modifiedDate;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "create_by")
    private String createBy;


}
