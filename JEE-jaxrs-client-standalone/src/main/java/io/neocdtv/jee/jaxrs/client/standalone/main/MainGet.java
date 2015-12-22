/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jaxrs.client.standalone.main;

import io.neocdtv.jee.jaxrs.client.standalone.ClientService;

/**
 *
 * @author xix
 */
public class MainGet {
    public static void main(String[] args) {
        final ClientService clientService = new ClientService();
        clientService.get();
    }
}
