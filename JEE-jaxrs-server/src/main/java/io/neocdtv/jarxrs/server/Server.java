/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import io.neocdtv.jerseyfiltershared.Object1;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author xix
 */
@Path("server")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Server {
    
    @POST
    public Response post(final Object1 filterObject) {
        filterObject.setFirst(filterObject.getFirst() + " - response");
        filterObject.setSecond(filterObject.getSecond() + " - response");
        return Response.ok(filterObject).build();
    }
}
