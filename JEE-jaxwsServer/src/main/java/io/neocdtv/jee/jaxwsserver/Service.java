/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jaxwsserver;

import io.neocdtv.jerseyfiltershared.Object1;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author xix
 */

@WebService
public class Service {
    
    @WebMethod
    public Object1 get() {
        return Object1.build();
    }
}
