
package io.neocdtv.jee.jaxwsclient.service;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "Service", targetNamespace = "http://jaxwsserver.jee.neocdtv.io/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Service {


    /**
     * 
     * @return
     *     returns io.neocdtv.jee.jaxwsclient.service.Object1
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "get", targetNamespace = "http://jaxwsserver.jee.neocdtv.io/", className = "io.neocdtv.jee.jaxwsclient.service.Get")
    @ResponseWrapper(localName = "getResponse", targetNamespace = "http://jaxwsserver.jee.neocdtv.io/", className = "io.neocdtv.jee.jaxwsclient.service.GetResponse")
    public Object1 get();

}
