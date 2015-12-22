/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.websocket.client;

import java.io.IOException;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.websocket.DeploymentException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author xix
 */

@Path("client")
@RequestScoped
public class ClientRS {
    
    @Inject
    private ClientConnection clientConnection;
    
    @GET
    @Path("connect")
    public String connect() throws DeploymentException, IOException {
        return clientConnection.connect();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> sessions() {
        return clientConnection.getSessionIds();
    }
    
    @GET
    @Path("sendMessage")
    public String sendMessage() throws DeploymentException, IOException {
        clientConnection.sendMessage();
        return "message send";
    }
    
    @GET
    @Path("disconnect") 
    public String disconnect() throws IOException {
        clientConnection.disconnect();
        return "disconnect";
    }
}
