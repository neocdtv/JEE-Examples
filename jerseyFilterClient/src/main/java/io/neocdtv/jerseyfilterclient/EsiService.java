/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jerseyfilterclient;

import io.neocdtv.jerseyfiltershared.Object1;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author xix
 */

@ApplicationScoped
@PerformanceLog
public class EsiService {

    private static final Client client;
    
    static {
        client = ClientBuilder.newClient();
        client.register(new ClientFilter());
        //client.register(new JerseyInterceptor());
    }
    
    public Object1 get() {
        return get(Object1.build(), buildTarget());
    }
    
    private Object1 get(final Object1 object1, final Invocation.Builder request) {
        final Object1 post = request.post(Entity.entity(object1, MediaType.APPLICATION_JSON), Object1.class);
        return post;
    }

    private Invocation.Builder buildTarget() {
        final WebTarget target = client.target("http://localhost:8080/jerseyFilterServer/rs/server");
        final Invocation.Builder request = target.request(MediaType.APPLICATION_JSON);
        return request;
    }
}
