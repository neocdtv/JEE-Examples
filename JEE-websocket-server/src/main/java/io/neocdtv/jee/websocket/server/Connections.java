/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.websocket.server;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.websocket.Session;

/**
 *
 * @author xix
 */

@ApplicationScoped
public class Connections {
    
    private final Logger logger = Logger.getLogger(ServiceRS.class.getName());
    private final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    public Set<String> getSessionIds() {
        final Set<String> sessionsSet = new HashSet<>();
        for (Session session : sessions) {
            sessionsSet.add(session.getId());
        }
        return sessionsSet;
    }
    
    public void sessionOpened(@Observes ConnectionOpened connectionOpened) {
        sessions.add(connectionOpened.getSession());
    }
    
    public void sessionsClosed(@Observes ConnectionClosed connectionClosed) {
        sessions.remove(connectionClosed.getSession());
    }
    
    public void disconnect() throws IOException {
        final HashSet<Session> copy = new HashSet<>(sessions);
        for (Session session : copy) {
            disconnectOne(session);
        }
    }

    public void disconnectOne(final Session session) throws IOException {
        logger.info(String.format("trying to disconnect %s", session.getId()));
        if (session.isOpen()) {
            session.close();
        }
    }
}
