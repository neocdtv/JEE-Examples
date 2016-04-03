/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author xix
 */
@ApplicationScoped

//@Consumes(MediaType.APPLICATION_JSON)
public class UnknownPropertiesMessageBodyReader extends JacksonJsonProvider {
    
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        
        return super.isReadable(type, genericType, annotations, mediaType);
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        final Object entity = ServerObjectMapper.INSTANCE.readValue(entityStream, type);
        return entity;
    }

    public ValidationException build(Class<Object> type, List<String> unknownEntityProperties) {
        final ValidationException exception = new ValidationException();
        for (String propertyName : unknownEntityProperties) {
            final ValidationConflict validationConflict = new ValidationConflict();
            validationConflict.setReason("UNKNOWN_PROPERTY");
            validationConflict.setRoot(type.getSimpleName());
            validationConflict.setSource(propertyName);
            validationConflict.setMessage("Unknown property");
            exception.addValidationConflict(validationConflict);
        }
        return exception;
    }
}
