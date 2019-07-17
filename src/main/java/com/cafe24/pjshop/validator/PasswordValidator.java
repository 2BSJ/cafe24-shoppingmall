package com.cafe24.pjshop.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.pjshop.validator.constraints.ValidPassword;


//8<x<20 , null값 금지, ""값 금지, 특수문자,영문 필수
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
   private Pattern pattern = Pattern.compile("(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}");
	//private Pattern pattern = Pattern.compile("/^[A-Za-z0-9]{8,20}$/");
	

   @Override
   public void initialize(ValidPassword constraintAnnotation) {
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