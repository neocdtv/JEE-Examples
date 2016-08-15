/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server.validation;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xix
 */
public class ValidationException extends RuntimeException {

  private List<ValidationConflict> validationConflicts;
  private List<JsonNode> messages;

  public List<ValidationConflict> getValidationConflicts() {
    if (validationConflicts == null) {
      validationConflicts = new ArrayList<>();
    }
    return validationConflicts;
  }

  public void addValidationConflict(final ValidationConflict validationConflict) {
    getValidationConflicts().add(validationConflict);
  }

  public List<JsonNode> getMessages() {
    return messages;
  }

  public void setMessages(List<JsonNode> messages) {
    this.messages = messages;
  }
  
  public boolean hasConflicts() {
    return validationConflicts != null && !validationConflicts.isEmpty();
  }
}
