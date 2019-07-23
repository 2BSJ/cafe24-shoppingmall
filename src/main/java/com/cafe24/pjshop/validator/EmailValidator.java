package com.cafe24.pjshop.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.pjshop.validator.constraints.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
	   private Pattern pattern = Pattern.compile("^[a-z]+[a-z0-9_+.-]+@([a-z0-9-]+\\.)+[a-z0-9]{2,4}$");
	   //영문자로 시작하는 email id + @ + 영문자 + . + 영문자로 끝나는 이메일

	   @Override
	   public void initialize(ValidEmail constraintAnnotation) {
	   }

	   @Override
	   public boolean isValid(String value, ConstraintValidatorContext context) {
		   //4자이상 20자 이하 , 비어있는값, 공백값 valid처리
	      if(value == null || "".contentEquals(value)) {
	         return false;
	      }
	      
	      return pattern.matcher(value).matches();
	   }

}
