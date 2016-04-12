/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import java.lang.annotation.Annotation;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author xix
 */
@ApplicationScoped
public class ValidationMapper {

    public ValidationException mapFromConstraintViolationException(final ConstraintViolationException constraintViolationException) {
        final ValidationException uspValidationException = new ValidationException();
        for (final ConstraintViolation constraintViolation : constraintViolationException.getConstraintViolations()) {
            if (constraintViolation != null) {
                final ValidationConflict validationConflict = mapFromConstraintViolation(constraintViolation);
                uspValidationException.addValidationConflict(validationConflict);
            }
        }
        return uspValidationException;
    }

    ValidationConflict mapFromConstraintViolation(final ConstraintViolation<Object> constraintViolation) {
        final String reason = getReason(constraintViolation);
        final String root = constraintViolation.getRootBeanClass().getSimpleName();
        final String source = String.valueOf(constraintViolation.getPropertyPath());
        final String invalidValue = String.valueOf(constraintViolation.getInvalidValue());
        final ValidationConflict validationConflict = new ValidationConflict();
        validationConflict.setReason(reason);
        validationConflict.setRoot(root);
        validationConflict.setSource(source);
        validationConflict.setInvalidValue(invalidValue);
        validationConflict.setMessage(constraintViolation.getMessage());
        return validationConflict;
    }

    private String getReason(final ConstraintViolation<Object> constraintViolation) {
        final Class<? extends Annotation> annotationType = constraintViolation.getConstraintDescriptor().getAnnotation().annotationType();
        final String annotationSimpleName = annotationType != null ? annotationType.getSimpleName() : "";
        return MyStringUtils.camelCaseToUpperCase(annotationSimpleName);
    }
}
