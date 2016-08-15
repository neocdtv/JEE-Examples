/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server.validation;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.customProperties.ValidationSchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.types.ContainerTypeSchema;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import io.neocdtv.jarxrs.server.ServerObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
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
public class ValidationJsonSchemaMessageBodyReader extends JacksonJsonProvider {

  private static final Logger LOGGER = Logger.getLogger(ValidationJsonSchemaMessageBodyReader.class.getName());
  ObjectMapper mapper = ServerObjectMapper.INSTANCE;

  @Override
  public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return super.isReadable(type, genericType, annotations, mediaType);
  }

  @Override
  public Object readFrom(Class<Object> type, Type genericType,
          Annotation[] annotations, MediaType mediaType,
          MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
          throws IOException, WebApplicationException {

    final String jsonData = getStringFromInputStream(entityStream);
    System.out.println(jsonData);

    JsonSchema jsonSchemaObject = generateJsonSchema(type);
    final ContainerTypeSchema asContainerTypeSchema = jsonSchemaObject.asContainerTypeSchema();

    String jsonSchemaFileAsString = mapper.writeValueAsString(asContainerTypeSchema);
    System.out.println(jsonSchemaFileAsString);
    try {
      validate(jsonSchemaFileAsString, jsonData);
    } catch (ProcessingException ex) {
      LOGGER.log(Level.SEVERE, null, ex);
    }
    Object addr = null;
    try {
      addr = mapper.readValue(entityStream, type);
    } catch (JsonMappingException e) {
      Logger.getLogger(ValidationJsonSchemaMessageBodyReader.class.getName()).log(Level.SEVERE, null, e);
    }
    return addr;
  }

  private JsonSchema generateJsonSchema(Class<Object> type) throws JsonMappingException {
    ValidationSchemaFactoryWrapper visitor = new ValidationSchemaFactoryWrapper();
    mapper.acceptJsonFormatVisitor(type, visitor);
    return visitor.finalSchema();
  }

  /**
   * Validate the given JSON data against the given JSON schema
   *
   * @param jsonSchema as String
   * @param jsonData as String
   * @throws MessageBodyReaderValidationException in case of an error during
   * validation process
   */
  private void validate(final String jsonSchema, final String jsonData) throws ProcessingException, IOException {

    final JsonNode data = JsonLoader.fromString(jsonData);
    final JsonNode schema = JsonLoader.fromString(jsonSchema);

    final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
    JsonValidator v = factory.getValidator();

    ProcessingReport report = v.validate(schema, data);
    final Iterator<ProcessingMessage> iterator = report.iterator();
    final List<JsonNode> messages = new ArrayList<>();
    while (iterator.hasNext()) {
      final ProcessingMessage processingMessage = iterator.next();
      messages.add(processingMessage.asJson());
    }
    
    // KW: this is temporarily think abount mapping to validationconflict or replace validationconflict with jsonSchema message
    if(!messages.isEmpty()) {
      final ValidationException validationException = new ValidationException();
      validationException.setMessages(messages);
      throw validationException;
    }
  }

  /**
   * Taken from <a href=
   * "http://www.mkyong.com/java/how-to-convert-inputstream-to-string-in-java/"
   * >www.mkyong.com</a>
   *
   * @param is {@link InputStream}
   * @return Stream content as String
   */
  private String getStringFromInputStream(InputStream is) {
    BufferedReader br = null;
    StringBuilder sb = new StringBuilder();

    String line;
    try {

      br = new BufferedReader(new InputStreamReader(is));
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return sb.toString();
  }
}
