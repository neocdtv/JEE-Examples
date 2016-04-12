/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author xix
 */
@javax.ws.rs.ApplicationPath("rs")
public class ServerApplicationConfig extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(JacksonFeature.class);
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(io.neocdtv.jarxrs.server.ObjectContextResolver.class);
        resources.add(io.neocdtv.jarxrs.server.Server.class);
        resources.add(io.neocdtv.jarxrs.server.ServerExceptionMapper.class);
        resources.add(io.neocdtv.jarxrs.server.ServerTrafficFilter.class);
        resources.add(io.neocdtv.jarxrs.server.UnknownPropertiesHandler.class);
        resources.add(io.neocdtv.jarxrs.server.ValidationJsonSchemaMessageBodyReader.class);
    }
    
}
