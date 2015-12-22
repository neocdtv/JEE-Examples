/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.websocket.server;

import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import org.apache.deltaspike.core.api.lifecycle.Destroyed;

/**
 *
 * @author xix
 */
@ApplicationScoped
public class ServiceEventHandler {

    @Inject
    private Connections connection;
    
    public void onDestroy(@Observes @Destroyed ServletContext context) throws IOException {
        System.out.println("Destroyed ServletContext: " + context.getServletContextName());
        connection.disconnect();
    }

}
