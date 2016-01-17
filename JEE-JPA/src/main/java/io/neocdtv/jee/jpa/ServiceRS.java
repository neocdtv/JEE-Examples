/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.neocdtv.eclipselink.entitygraph.CopyPartialEntities;
import io.neocdtv.eclipselink.entitygraph.PathsToEntityGraphConverter;
import io.neocdtv.jee.jpa.entity.onetomany.bi.ParentBiOne;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.persistence.internal.jpa.EntityGraphImpl;

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

    @POST
    public Response create(final ParentBiOne parent) {
        service.create(parent);
        return Response.ok().build();
    }

    @PUT
    public Response update(final ParentBiOne parent) {
        parent.setChildren(parent.getChildren());
        service.update(parent);
        return Response.ok().build();
    }

    @PUT
    @Path("partial")
    public Response updatePartial(final ParentBiOne parent) throws JsonProcessingException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final ParentBiOne updatePartial = service.updatePartial(parent, null);
        return Response.ok(updatePartial).build();
    }

    @GET
    @Path("{id}")
    public Response read(@PathParam("id") Long id) {
        final ParentBiOne parent = service.read(id);
        return Response.ok(parent).build();
    }

    @GET
    @Path("partial/{id}")
    public Response readPartial(@HeaderParam("partial") String partial, @PathParam("id") Long id) throws JsonProcessingException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException {
        final String[] paths = partial.split(",");       
        final EntityGraph<ParentBiOne> entityGraph = new PathsToEntityGraphConverter().toGraph((EntityGraphImpl) em.createEntityGraph(ParentBiOne.class), paths);
        
        final ParentBiOne parent = service.readPartial(id, entityGraph);       
        final ParentBiOne newParent = new CopyPartialEntities().copy(parent, (EntityGraphImpl) entityGraph);
        
        return Response.ok(newParent).build();
    }

    @GET
    public Response read() {
        final List<ParentBiOne> parents = service.read();
        return Response.ok(parents).build();
    }
}
