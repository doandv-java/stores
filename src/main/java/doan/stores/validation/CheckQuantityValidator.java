package doan.stores.validation;

import doan.stores.validation.annotation.CheckQuantity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckQuantityValidator implements ConstraintValidator<CheckQuantity, Object> {
    private long quantity;
    private String message;

    @Override
    public void initialize(CheckQuantity constraintAnnotation) {
        quantity = constraintAnnotation.quantity();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;

        return false;
    }
}
