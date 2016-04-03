/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Logger;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author xix
 */

@Provider
public class ObjectContextResolver implements ContextResolver<ObjectMapper> {

    private static final Logger LOGGER = Logger.getLogger(ObjectContextResolver.class.getName());
    
    @Override
    public ObjectMapper getContext(final Class<?> type) {
        return ServerObjectMapper.INSTANCE;
    }    
}
