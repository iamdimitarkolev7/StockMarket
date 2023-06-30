package com.kolev.stock.app.myapp.utils;

import jakarta.websocket.*;

import java.io.IOException;

@ClientEndpoint
public class WebSocketClient {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket connection opened");

        try {
            // Subscribe to stock symbols for real-time data
            session.getBasicRemote().sendText("{\"action\": \"subscribe\", \"symbols\": \"AMZN, TSLA, APPL, NIO, CO, MO\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Received message: " + message);
        System.out.println("LG");
    }

    @OnError
    public void onError(Throwable throwable) {
        System.err.println("WebSocket error: " + throwable.getMessage());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("WebSocket connection closed: " + closeReason.getReasonPhrase());
    }
}
