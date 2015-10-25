/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jerseyfilterclient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author xix
 */

@Path("server")
@ApplicationScoped
public class Server {
    
    @Inject
    private EsiService service;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        return service.get().getFirst();
    }
    
    @GET
    @Path("enum")
    @Produces(MediaType.APPLICATION_JSON)
    public ConditionProvider getEnum() {
        return ConditionProvider.KEEPER;
    }
    
    @POST
    @Path("enum")
    @Consumes(MediaType.APPLICATION_JSON)
    public void getEnum(final ConditionProvider conditionProvider) {
        System.out.println("XXX: " + conditionProvider);
    }
        
}
