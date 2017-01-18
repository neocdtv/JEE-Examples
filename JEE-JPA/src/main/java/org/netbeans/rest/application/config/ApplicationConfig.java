/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import javax.ws.rs.Priorities;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.message.filtering.SelectableEntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author xix
 */
@javax.ws.rs.ApplicationPath("rs")
public class ApplicationConfig extends ResourceConfig {

  public ApplicationConfig() {
    
    // configure entity filtering
    register(SelectableEntityFilteringFeature.class, Priorities.HEADER_DECORATOR);
    property(SelectableEntityFilteringFeature.QUERY_PARAM_NAME, RequestParameter.QUERY_PARAM_ENTITY_FILTERING);

    // enable jackson instead
    register(JacksonFeature.class);
    // configure jackson
    //register(io.neocdtv.jee.jpa.JacksonProvider.class);
    
    register(io.neocdtv.jee.jpa.ServiceRS.class);
    
    
  }

}
