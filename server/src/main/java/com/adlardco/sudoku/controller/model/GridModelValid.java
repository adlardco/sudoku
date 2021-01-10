package com.adlardco.sudoku.controller.model;

import com.adlardco.sudoku.service.grid.GridModelValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;
import java.util.List;

@Documented
@Constraint(validatedBy = GridModelValidator.class)
@Target({ ElementType.TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface GridModelValid {
    String message() default "Invalid grid";

    Class<?>[] groups() default {};

    Class<? extends List<Integer>>[] payload() default {};
}
