/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.websocket.client;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author xix
 */
@RequestScoped
public class ClientConnection {

    private final Logger logger = Logger.getLogger(ClientConnection.class.getName());
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    
    @Inject
    private Event<ConnectionOpened> connectionOpened;

    @Inject
    private Event<ConnectionClosed> connectionClosed;

    
    public String connect() throws DeploymentException, IOException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://localhost:9380/JEE-websocket-server/websocket";
        Client client = new Client();
        client.setConnectionOpened(connectionOpened);
        client.setConnectionClosed(connectionClosed);
        final Session clientSession = container.connectToServer(client, URI.create(uri));
        sessions.add(clientSession);
        return "connection success";
    }

    public void sendMessage() throws IOException {
        for (Session session : sessions) {
            final String message = String.format("%s", new Date().toString());
            logger.info(String.format("sending message %s on client session %s", message, session.getId()));
            session.getBasicRemote().sendText(message);
        }
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

    public Set<String> getSessionIds() {
        final Set<String> sessionsSet = new HashSet<>();
        for (Session session : sessions) {
            sessionsSet.add(session.getId());
        }
        return sessionsSet;
    }
}
