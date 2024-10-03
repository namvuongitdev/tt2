package com.example.finally2.constraint.order;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExportOrder {
    int value();

}
