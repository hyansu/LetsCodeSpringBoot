package com.aula04.banco.banco.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SENHAValidador implements ConstraintValidator<SENHA, String> {
    @Override
    public void initialize(SENHA constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String senha, ConstraintValidatorContext context) {
        if(senha.length() >= 6){
            Pattern letter = Pattern.compile("[A-Z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]");

            Matcher hasLetter = letter.matcher(senha);
            Matcher hasDigit = digit.matcher(senha);
            Matcher hasSpecial = special.matcher(senha);

            if(hasLetter.find() && hasDigit.find() && hasSpecial.find()){
                return true;
            }
        }
        return false;
    }
}
