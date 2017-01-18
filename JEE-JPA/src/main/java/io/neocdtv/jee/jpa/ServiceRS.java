/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.neocdtv.domain.customer.Customer;
import io.neocdtv.eclipselink.entitygraph.PathsToEntityGraphConverter;
import java.lang.reflect.InvocationTargetException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.persistence.internal.jpa.EntityGraphImpl;
import org.netbeans.rest.application.config.RequestParameter;

/**
 * 
 * @author xix
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("service")
public class ServiceRS {

  @Inject
  private Service service;

  @PersistenceContext(unitName = "jpaPlay")
  private EntityManager em;

  /* 
    
  */
  @GET
  @Path("{id}")
  public Response read(@QueryParam(RequestParameter.QUERY_PARAM_ENTITY_FILTERING) String select, @PathParam("id") Long id) throws JsonProcessingException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException {
    final Customer parent;
    if (select == null) {
      parent = service.read(Customer.class, id);
    } else {
      final String[] paths = select.split(",");
      final EntityGraph<Customer> entityGraph
              = new PathsToEntityGraphConverter()
              .toGraph((EntityGraphImpl) em.createEntityGraph(Customer.class), paths);
      parent = service.readPartial(id, entityGraph);
    }

    return Response.ok(parent).build();
  }
}
