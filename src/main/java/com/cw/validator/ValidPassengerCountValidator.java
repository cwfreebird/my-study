package com.cw.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author david.cai
 * @date 2019/5/30
 */
public class ValidPassengerCountValidator implements ConstraintValidator<ValidPassengerCount, Car> {
    @Override
    public void initialize(ValidPassengerCount constraintAnnotation) {

    }

    @Override
    public boolean isValid(Car car, ConstraintValidatorContext context) {
        return car.getSeat() >= car.getPersons().size();
    }
}
