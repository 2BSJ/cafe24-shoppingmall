package com.cafe24.pjshop.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cafe24.pjshop.validator.IdValidator;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy=IdValidator.class)
public @interface ValidId {
	String message() default "Invalid Id";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
