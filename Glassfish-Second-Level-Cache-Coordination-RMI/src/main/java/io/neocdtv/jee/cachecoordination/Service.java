/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.cachecoordination;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author xix
 */
@Path("service")
@ApplicationScoped
public class Service {
    
    @PersistenceContext(unitName = "cachedCoordinationUnit")
    protected EntityManager em;

    @GET
    @Path("{id}")
    public CachedProperty read(@QueryParam("id") String id) {
        return em.find(CachedProperty.class, id);
    }

    @POST
    public void create(final CachedProperty cachedProperty) {
        em.persist(cachedProperty);
    }

    @PUT
    public void upadate(final CachedProperty cachedProperty) {
        em.merge(cachedProperty);
    }
 
}
