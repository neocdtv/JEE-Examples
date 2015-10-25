
package io.neocdtv.jee.jaxwsclient.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the io.neocdtv.jee.jaxwsclient.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetResponse_QNAME = new QName("http://jaxwsserver.jee.neocdtv.io/", "getResponse");
    private final static QName _Get_QNAME = new QName("http://jaxwsserver.jee.neocdtv.io/", "get");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: io.neocdtv.jee.jaxwsclient.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Object2 }
     * 
     */
    public Object2 createObject2() {
        return new Object2();
    }

    /**
     * Create an instance of {@link Object1 }
     * 
     */
    public Object1 createObject1() {
        return new Object1();
    }

    /**
     * Create an instance of {@link GetResponse }
     * 
     */
    public GetResponse createGetResponse() {
        return new GetResponse();
    }

    /**
     * Create an instance of {@link Get }
     * 
     */
    public Get createGet() {
        return new Get();
    }

    /**
     * Create an instance of {@link Object3 }
     * 
     */
    public Object3 createObject3() {
        return new Object3();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxwsserver.jee.neocdtv.io/", name = "getResponse")
    public JAXBElement<GetResponse> createGetResponse(GetResponse value) {
        return new JAXBElement<GetResponse>(_GetResponse_QNAME, GetResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Get }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxwsserver.jee.neocdtv.io/", name = "get")
    public JAXBElement<Get> createGet(Get value) {
        return new JAXBElement<Get>(_Get_QNAME, Get.class, null, value);
    }

}
