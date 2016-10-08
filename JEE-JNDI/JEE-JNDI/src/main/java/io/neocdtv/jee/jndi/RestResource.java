/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jndi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author xix
 */

@Path("rest")
public class RestResource {
  
  @GET
  public String bla() {
    return nix();
  } 

  public String nix() {
    return "nix";
  }
}
