package ua.ros.taxiapp.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<CheckMobile,String> {


    @Override
    public void initialize(CheckMobile phone) { }

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext cxt) {
        if(phoneField == null) {
            return false;
        }
        return phoneField.matches("\\+[0-9]{12}");
    }
}