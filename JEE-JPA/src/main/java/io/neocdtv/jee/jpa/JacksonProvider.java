package io.neocdtv.jee.jpa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * TODO javadoc.
 *
 * @author Jakub Podlesak (jakub.podlesak at oracle.com)
 */
@Provider
public class JacksonProvider implements ContextResolver<ObjectMapper> {

    public static final ObjectMapper defaultObjectMapper = new ObjectMapper();

    static {
        defaultObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        defaultObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }
    
    public JacksonProvider() {
    }

    @Override
    public ObjectMapper getContext(final Class<?> type) {
        return defaultObjectMapper;
    }
}
