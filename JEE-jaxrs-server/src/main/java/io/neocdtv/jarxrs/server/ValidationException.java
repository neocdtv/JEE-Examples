/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author xix
 */
public class ValidationException extends RuntimeException {

    private List<ValidationConflict> validationConflicts;

    public List<ValidationConflict> getValidationConflicts() {
        if (validationConflicts == null) {
            validationConflicts = new ArrayList<>();
        }
        return validationConflicts;
    }

    public void addValidationConflict(final ValidationConflict validationConflict) {
        getValidationConflicts().add(validationConflict);
    }
}
