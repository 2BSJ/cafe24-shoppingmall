package com.cafe24.pjshop.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.pjshop.validator.constraints.ValidPhone;


//010(무조건3글자) - 1111(무조건4글자) - 2222(무조건4글자)
public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
   private Pattern pattern = Pattern.compile("^\\d{3}\\-\\d{4}\\-\\d{4}$");
	
	

   @Override
   public void initialize(ValidPhone constraintAnnotation) {
   }

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {
      if(value == null || "".contentEquals(value)) {
         return false;
      }
      
      return pattern.matcher(value).matches();
   }

}