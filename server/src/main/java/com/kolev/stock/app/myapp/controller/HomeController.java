package com.kolev.stock.app.myapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    private final Map<String, Double> stocks = new HashMap<>();
    private final SseEmitter emitter = new SseEmitter();

    @GetMapping("/api/home")
    public SseEmitter getHome() {

        String url = "ws://ws.eodhistoricaldata.com/ws/us?api_token=demo";
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        try {
            Session session = container.connectToServer(new WebSocketClient(), URI.create(url));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emitter;
    }

    @ClientEndpoint
    private class WebSocketClient extends Endpoint {

        @Override
        public void onOpen(Session session, EndpointConfig config) {

            try {
                session.getBasicRemote().sendText("{\"action\": \"subscribe\", \"symbols\": \"AMZN, TSLA\"}");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            session.addMessageHandler((MessageHandler.Whole<String>) message -> {
                try {
                    Map<String, Object> result = new ObjectMapper().readValue(message, HashMap.class);
                    System.out.println(result.get("p"));
                    System.out.println(result.get("s"));

                    stocks.put((String) result.get("s"), ((Number) result.get("p")).doubleValue());

                    // Send the updated stocks map to the SSE emitter
                    updateSseEmitter();
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        @Override
        public void onClose(Session session, CloseReason closeReason) {
            System.out.println("WebSocket connection closed: " + closeReason.getReasonPhrase());
        }

        @Override
        public void onError(Session session, Throwable throwable) {
            System.out.println("WebSocket error: " + throwable.getMessage());
        }

        private void updateSseEmitter() {
            try {
                emitter.send(stocks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
