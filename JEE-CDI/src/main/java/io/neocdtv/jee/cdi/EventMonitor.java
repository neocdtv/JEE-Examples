/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.cdi;

import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.servlet.ServletContext;
import org.apache.deltaspike.core.api.lifecycle.Destroyed;
import org.apache.deltaspike.core.api.lifecycle.Initialized;


/**
 *
 * @author xix
 */

@ApplicationScoped
public class EventMonitor {
    
    private static final Logger logger = Logger.getLogger(EventMonitor.class.getName());
    //
    
    public void onCreate(@Observes @Initialized ServletContext context) {
        System.out.println("Initialized ServletContext: " + context.getServletContextName());
    }

    public void onDestroy(@Observes @Destroyed ServletContext context) {
        System.out.println("Destroyed ServletContext: " + context.getServletContextName());
    }
    
}
