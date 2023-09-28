package com.kolev.stock.app.myapp.controller;

import com.kolev.stock.app.myapp.models.requests.portfolio.BuyStockRequest;
import com.kolev.stock.app.myapp.models.requests.portfolio.SellStockRequest;
import com.kolev.stock.app.myapp.models.responses.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PortfolioController {

    //private final PortfolioService portfolioService;

    @GetMapping("/api/users/portfolio/{id}")
    public ResponseEntity<Response> getPortfolioById(@PathVariable("id") Long userId) {
        return null;
    }

    @PostMapping("/api/users/portfolio/buy_stock")
    public ResponseEntity<Response> buyStock(@RequestBody BuyStockRequest request) {
        return null;
    }

    @PostMapping("/api/users/portfolio/sell_stock")
    public ResponseEntity<Response> sellStock(@RequestBody SellStockRequest request) {
        return null;
    }
}
