package com.cafe24.pjshop.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.pjshop.validator.constraints.ValidId;


//영어로시작하고 총 5글자 이상 10글자 이하의 아이디 특수문자 불가함
public class IdValidator implements ConstraintValidator<ValidId, String> {
   private Pattern pattern = Pattern.compile("^[a-z]+[a-z0-9]{4,10}$");
	
	

   @Override
   public void initialize(ValidId constraintAnnotation) {
   }

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {
      if(value == null || "".contentEquals(value) || value.length() < 5) {
         return false;
      }
      
      return pattern.matcher(value).matches();
   }

}