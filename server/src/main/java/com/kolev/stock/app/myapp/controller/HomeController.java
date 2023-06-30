package com.kolev.stock.app.myapp.controller;

import com.kolev.stock.app.myapp.models.PortfolioStock;
import com.kolev.stock.app.myapp.models.responses.Response;
import com.kolev.stock.app.myapp.utils.WebSocketClient;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static java.util.Map.of;

@RestController
public class HomeController {

    @GetMapping("/api/home")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Response> getHome() throws IOException, InterruptedException {

        String url = "ws://ws.eodhistoricaldata.com/ws/us?api_token=demo";
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        try {
            Session session = container.connectToServer(WebSocketClient.class, URI.create(url));

            if (session.isOpen()) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(
                Response.builder()
                        .data(of("stocks", List.of(1,2,3)))
                        .message("Stocks retrieved!")
                        .build()
        );
    }
}
