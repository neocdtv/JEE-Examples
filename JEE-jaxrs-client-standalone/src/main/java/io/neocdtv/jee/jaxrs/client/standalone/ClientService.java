/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jaxrs.client.standalone;

import io.neocdtv.datamodel.Object1;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author xix
 */

public class ClientService {
    
    public Object1 post() {
        return post(Object1.build(), buildTarget());
    }
    
    public Response get() {
        return buildTarget().get();
    }
    
    private Object1 post(final Object1 object1, final Invocation.Builder request) {
        final Object1 post = request.post(Entity.entity(object1, MediaType.APPLICATION_JSON), Object1.class);
        return post;
    }

    private Invocation.Builder buildTarget() {
        final WebTarget target = ClientFactory.INSTANCE.target("http://localhost:9380/JEE-jaxrs-server/rs/server");
        final Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        return request;
    }
}
