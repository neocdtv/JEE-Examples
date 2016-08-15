/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import io.neocdtv.jarxrs.server.model.PojoFirst;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.customProperties.ValidationSchemaFactoryWrapper;
import com.sun.codemodel.JCodeModel;
import io.neocdtv.datamodel.Object1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import java.io.File;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;

/**
 *
 * @author xix
 */

@Api(value = "/validation")
@Path("validation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ResourceExample {

  @GET
  @Path("schema-jackson")
  public Response generateSchemaJackson() throws JsonMappingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

    final com.fasterxml.jackson.databind.jsonschema.JsonSchema generateJsonSchema = mapper.generateJsonSchema(Object1.class);

    return Response.ok(mapper.writeValueAsString(generateJsonSchema)).build();
  }

  @GET
  @Path("schema-jackson-plugin")
  public Response generateSchemaJacksonPlugin() throws JsonMappingException, IOException {
    ValidationSchemaFactoryWrapper visitor = new ValidationSchemaFactoryWrapper();
    ObjectMapper mapper = ServerObjectMapper.INSTANCE;
    mapper.acceptJsonFormatVisitor(Object1.class, visitor);
    JsonSchema jsonSchema = visitor.finalSchema();
    return Response.ok(jsonSchema.asContainerTypeSchema()).build();
  }

  // not working - TODO fix
  @GET
  @Path("schema-custom")
  public Response generateSchemaCustom() throws JsonMappingException, IOException {

    GenerationConfig config = new DefaultGenerationConfig() {
      @Override
      public boolean isGenerateBuilders() { // set config option by overriding method
        return true;
      }

      @Override
      public boolean isIncludeJsr303Annotations() { // set config option by overriding method
        return true;
      }

    };
    JCodeModel codeModel = new JCodeModel();

    SchemaGenerator schemaGenerator = new SchemaGenerator();
    SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(), new SchemaStore()), schemaGenerator);

    mapper.generate(codeModel, "Object1", "io.neocdtv.datamodel", "null");
    codeModel.build(new File("/home/xix/Desktop/output.txt"));

    return null;
  }

  /*
   * use post to 
   * - simulate long running requests
   * - look how thread pools are working -> if you set http-thread-pool to 5 threads, 
   *   start 5 requests and the sixt should block und one thread from the thread pool is available again.
   *       configurations -> server config -> thread poools -> http-thread-pool
   */
  
  @ApiOperation(value = "Find purchase order by ID",
          notes = "For valid response try integer IDs with value <= 5 or > 10. Other values will generated exceptions",
          response = PojoFirst.class)
  @POST
  public Response post(@ApiParam(required = true) final PojoFirst filterObject) throws InterruptedException {
    return Response.ok(filterObject).build();
  }
}
