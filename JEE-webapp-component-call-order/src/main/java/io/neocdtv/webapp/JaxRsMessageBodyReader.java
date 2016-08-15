/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.webapp;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author xix
 */
@ApplicationScoped
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class JaxRsMessageBodyReader extends JacksonJsonProvider {

  private static final Logger LOGGER = Logger.getLogger(JaxRsMessageBodyReader.class.getName());

  @Override
  public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return super.isReadable(type, genericType, annotations, mediaType);
  }

  @Override
  public Object readFrom(Class<Object> type, Type genericType,
          Annotation[] annotations, MediaType mediaType,
          MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
          throws IOException, WebApplicationException {
    LOGGER.info("JaxRsMessageBodyReader.readFrom");
    return super.readFrom(type, genericType, annotations, mediaType, httpHeaders, entityStream);
  }
}
