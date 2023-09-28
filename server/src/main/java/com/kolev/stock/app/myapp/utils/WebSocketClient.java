package com.kolev.stock.app.myapp.utils;

import jakarta.websocket.*;

import java.io.IOException;

//session.getBasicRemote().sendText("{\"action\": \"subscribe\", \"symbols\": \"AMZN, TSLA\"}");

@ClientEndpoint
public class WebSocketClient {

    @OnOpen
    public void onOpen(Session session) throws InterruptedException {
        System.out.println("WebSocket connection opened");

        try {
            session.getBasicRemote().sendText("{\"action\": \"subscribe\", \"symbols\": \"AMZN, TSLA\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
