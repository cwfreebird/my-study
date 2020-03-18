package com.cw.validator;

import com.thoughtworks.paranamer.AnnotationParanamer;
import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.parameternameprovider.ParanamerParameterNameProvider;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

/**
 * @author david.cai
 * @date 2019/5/29
 */
@Slf4j
public class ValidationUtils {
    private static Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .parameterNameProvider(new ParanamerParameterNameProvider(
                    new AnnotationParanamer(
                            new BytecodeReadingParanamer())))
            .buildValidatorFactory()
            .getValidator();

    public static <T> void valid(T obj){
        Set<ConstraintViolation<T>> violations = validator.validate(obj);

        if (!violations.isEmpty()){
            ConstraintViolation<T> violation = violations.iterator().next();
            throw new RuntimeException(violation.getPropertyPath() + " " + violation.getMessage());
        }
    }

    public static <T> void validMethod(T obj, Method method, Object[] parameterValues){
        Set<ConstraintViolation<T>> violations = validator.forExecutables().validateParameters(obj, method, parameterValues);

        if (!violations.isEmpty()){
            ConstraintViolation<T> violation = violations.iterator().next();
            throw new RuntimeException(violation.getPropertyPath() + " " + violation.getMessage());
        }
    }
}
