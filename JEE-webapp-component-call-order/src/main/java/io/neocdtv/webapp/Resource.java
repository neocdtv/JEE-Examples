/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.webapp;

import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author xix
 */

@Path("example")
public class Resource {
  
  private static final Logger LOGGER = Logger.getLogger(Resource.class.getName());
  
  
  @GET
  public Response exampleGet() {
    LOGGER.info("Resource.example(get)");
    return Response.ok("OK").build();
  }

  @GET
  @Path("exception")
  public Response exampleGetWithException() {
    LOGGER.info("Resource.example(get,exception)");
    throw new RuntimeException("Resource.example(get,exception)");
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response examplePost(Object object) {
    LOGGER.info("Resource.example(post)");
    return Response.ok(object).build();
  }
}
