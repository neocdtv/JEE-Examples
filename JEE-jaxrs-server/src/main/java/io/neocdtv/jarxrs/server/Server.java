/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.customProperties.ValidationSchemaFactoryWrapper;
import io.neocdtv.datamodel.Object1;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;

/**
 *
 * @author xix
 */
@Path("server")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Server {
    
    @Context
    

    @GET
    @Path("schema-jackson")
    public Response generateSchemaJackson() throws JsonMappingException, IOException {
        ValidationSchemaFactoryWrapper visitor = new ValidationSchemaFactoryWrapper();
        ObjectMapper mapper = ServerObjectMapper.INSTANCE;
        mapper.acceptJsonFormatVisitor(Object1.class, visitor);
        JsonSchema jsonSchema = visitor.finalSchema();

        return Response.ok(jsonSchema.asContainerTypeSchema()).build();
    }

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
/*
         JCodeModel codeModel = new JCodeModel();
        
         SchemaGenerator schemaGenerator = new SchemaGenerator();
         SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(), new SchemaStore()), schemaGenerator);
        
         mapper.generate(codeModel, "Object1", "io.neocdtv.datamodel", "null");
         codeModel.build(new File("output"));
*/
         return null;
    }

    /*
     * use post to 
     * - simulate long running requests
     * - look how thread pools are working -> if you set http-thread-pool to 5 threads, 
     *   start 5 requests and the sixt should block und one thread from the thread pool is available again.
     *       configurations -> server config -> thread poools -> http-thread-pool
     */
    @POST
    public Response post(final PojoFirst filterObject) throws InterruptedException {
        //Thread.sleep(TimeUnit.SECONDS.toMillis(60));
        return Response.ok(filterObject).build();
    }
}
