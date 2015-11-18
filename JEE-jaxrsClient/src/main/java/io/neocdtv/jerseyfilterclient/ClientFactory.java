/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jerseyfilterclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 *
 * @author xix
 */
public class ClientFactory {
    public static final Client INSTANCE = ClientBuilder.newClient();;

    static {
        INSTANCE.register(new ClientTrafficFilter());
    }

    private ClientFactory() {
        // prevent instance
    }
}
