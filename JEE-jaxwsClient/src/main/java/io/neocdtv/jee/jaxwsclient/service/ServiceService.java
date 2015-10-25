
package io.neocdtv.jee.jaxwsclient.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "ServiceService", targetNamespace = "http://jaxwsserver.jee.neocdtv.io/", wsdlLocation = "file:/home/xix/Projects/JEE-jaxwsClient/src/main/webapp/WEB-INF/wsdl/ServiceService.xml")
public class ServiceService
    extends javax.xml.ws.Service
{

    private final static URL SERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(io.neocdtv.jee.jaxwsclient.service.ServiceService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = io.neocdtv.jee.jaxwsclient.service.ServiceService.class.getResource(".");
            url = new URL(baseUrl, "file:/home/xix/Projects/JEE-jaxwsClient/src/main/webapp/WEB-INF/wsdl/ServiceService.xml");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'file:/home/xix/Projects/JEE-jaxwsClient/src/main/webapp/WEB-INF/wsdl/ServiceService.xml', retrying as a local file");
            logger.warning(e.getMessage());
        }
        SERVICESERVICE_WSDL_LOCATION = url;
    }

    public ServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServiceService() {
        super(SERVICESERVICE_WSDL_LOCATION, new QName("http://jaxwsserver.jee.neocdtv.io/", "ServiceService"));
    }

    /**
     * 
     * @return
     *     returns Service
     */
    @WebEndpoint(name = "ServicePort")
    public io.neocdtv.jee.jaxwsclient.service.Service getServicePort() {
        return super.getPort(new QName("http://jaxwsserver.jee.neocdtv.io/", "ServicePort"), Service.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Service
     */
    @WebEndpoint(name = "ServicePort")
    public io.neocdtv.jee.jaxwsclient.service.Service getServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://jaxwsserver.jee.neocdtv.io/", "ServicePort"), Service.class, features);
    }

}
