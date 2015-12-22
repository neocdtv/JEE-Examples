/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.websocket.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Event;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author xix
 */
@ClientEndpoint
public class Client {

    private final Logger logger = Logger.getLogger(Client.class.getName());

    private Event<ConnectionOpened> connectionOpened;
    private Event<ConnectionClosed> connectionClosed;
   
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        logger.info(String.format("opening session %s on client, isOpen: %s", session.getId(), session.isOpen()));
        session.getBasicRemote().sendPing(null);
        connectionOpened.fire(new ConnectionOpened(session));
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        logger.info(String.format("closing session %s on client", session.getId()));
        connectionClosed.fire(new ConnectionClosed(session));
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        logger.info(String.format("receiving message %s on client session %s", message, session.getId()));
        return message;
    }

    @OnError
    public void onError(Throwable exception, Session session) {
        logger.log(Level.SEVERE, String.format("some error on server session %s", session.getId()), exception);
    }

    public void setConnectionOpened(Event<ConnectionOpened> connectionOpened) {
        this.connectionOpened = connectionOpened;
    }

    public void setConnectionClosed(Event<ConnectionClosed> connectionClosed) {
        this.connectionClosed = connectionClosed;
    }
    
    
}
