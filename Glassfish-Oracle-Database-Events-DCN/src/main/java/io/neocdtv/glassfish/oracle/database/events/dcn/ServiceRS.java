/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.glassfish.oracle.database.events.dcn;

import java.sql.SQLException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author xix
 */
@Path("service")
@ApplicationScoped
public class ServiceRS {
    
    @Inject
    private Service service;

    @GET
    @Path("{id}")
    public CachedProperty read(@PathParam("id") String id) {
        return service.read(id);
    }

    @POST
    public void create(final CachedProperty cachedProperty) {
        service.create(cachedProperty);
    }

    @PUT
    public void upadate(final CachedProperty cachedProperty) throws SQLException {
        service.updatejdbc(cachedProperty);
    }
 
}
