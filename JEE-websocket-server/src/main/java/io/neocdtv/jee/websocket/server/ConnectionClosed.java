/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.websocket.server;

import javax.websocket.Session;

/**
 *
 * @author xix
 */
public class ConnectionClosed {
    private final Session session;

    public ConnectionClosed(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }
}
