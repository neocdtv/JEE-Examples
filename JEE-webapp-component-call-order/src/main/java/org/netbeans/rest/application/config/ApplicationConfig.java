/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author xix
 */
@javax.ws.rs.ApplicationPath("rs")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new java.util.HashSet<>();
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
    resources.add(io.neocdtv.webapp.JaxRsExceptionMapper.class);
    resources.add(io.neocdtv.webapp.JaxRsFilter.class);
    resources.add(io.neocdtv.webapp.JaxRsInterceptor.class);
    resources.add(io.neocdtv.webapp.JaxRsMessageBodyReader.class);
    resources.add(io.neocdtv.webapp.JaxRsMessageBodyWriter.class);
    resources.add(io.neocdtv.webapp.Resource.class);
  }
  
}
