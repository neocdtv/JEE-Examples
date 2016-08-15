/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author xix
 */
@ApplicationScoped
public class ValidationService {

    @Inject
    private ValidationMapper validationMapper;

    @Resource
    private Validator validator;

    public void validate(final List<Object> toBeValidated) {
        ValidationException validationException = null;
        for (final Object validationRequired : toBeValidated) {
            final List<ValidationConflict> validationConflicts = getValidationConflicts(validationRequired);
            for (final ValidationConflict validationConflict : validationConflicts) {
                if (validationException == null) {
                    validationException = new ValidationException();
                }
                validationException.addValidationConflict(validationConflict);
            }
        }
        if (validationException != null) {
            throw validationException;
        }
    }

    private List<ValidationConflict> getValidationConflicts(final Object validationRequired) {
        final List<ValidationConflict> validationConflicts = new ArrayList<>();
        final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(validationRequired);
        for (final ConstraintViolation<Object> constraintViolation : constraintViolations) {
            final ValidationConflict validationConflict = validationMapper.mapFromConstraintViolation(constraintViolation);
            validationConflicts.add(validationConflict);
        }
        return validationConflicts;
    }
}
