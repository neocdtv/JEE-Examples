/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.websocket.client;

import javax.websocket.Session;

/**
 *
 * @author xix
 */
public class ConnectionOpened {
    private final Session session;

    public ConnectionOpened(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
