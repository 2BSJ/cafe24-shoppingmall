package com.cafe24.pjshop.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.pjshop.validator.constraints.ValidName;


//가부터 힣까지 2글자이상 5글자이하
public class NameValidator implements ConstraintValidator<ValidName, String> {
   private Pattern pattern = Pattern.compile("^[가-힣]{2,5}$");
	
	

   @Override
   public void initialize(ValidName constraintAnnotation) {
   }

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {
      if(value == null || "".contentEquals(value)) {
         return false;
      }
      
      return pattern.matcher(value).matches();
   }

}