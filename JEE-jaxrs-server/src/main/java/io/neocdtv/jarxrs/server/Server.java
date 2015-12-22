/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import io.neocdtv.datamodel.Object1;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
    
    @GET
    public String get() {
        return "response for get";
    }
    
    /*
    * use post to 
    * - simulate long running requests
    * - look how thread pools are working -> if you set http-thread-pool to 5 threads, 
    *   start 5 requests and the sixt should block und one thread from the thread pool is available again.
    *       configurations -> server config -> thread poools -> http-thread-pool
    */
    @POST
    public Response post(final Object1 filterObject) throws InterruptedException {
        Thread.sleep(TimeUnit.SECONDS.toMillis(60));
        filterObject.setFirst(filterObject.getFirst() + " - response");
        filterObject.setSecond(filterObject.getSecond() + " - response");
        return Response.ok(filterObject).build();
    }
}
