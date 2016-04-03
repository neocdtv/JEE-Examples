/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import java.io.IOException;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author xix
 */

@Provider
public class UnknownPropertiesHandler extends DeserializationProblemHandler {


    @Override
    public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser jp,
            JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName)
            throws IOException, JsonProcessingException {

        System.out.println("XXX unknown property: " + beanOrClass.getClass().getCanonicalName() + "['" + propertyName + "']");
        return true;
    }
}
