package com.kolev.stock.app.myapp.controller;

import com.kolev.stock.app.myapp.exceptions.wallets.WalletNotFoundException;
import com.kolev.stock.app.myapp.models.Wallet;
import com.kolev.stock.app.myapp.models.requests.wallet.WalletRequest;
import com.kolev.stock.app.myapp.models.responses.Response;
import com.kolev.stock.app.myapp.service.interfaces.UserService;
import com.kolev.stock.app.myapp.service.interfaces.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Map.of;

@RestController
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/api/users/wallet/{id}")
    public ResponseEntity<Response> getWalletById(@PathVariable("id") Long userId) {

        try {
            Optional<Wallet> o_wallet = walletService.getWalletByUserId(userId);

            if (o_wallet.isEmpty()) {
                throw new WalletNotFoundException("Wallet not found!");
            }

            return ResponseEntity.ok(
                    Response.builder().timeStamp(LocalDateTime.now())
                            .data(of("wallet", o_wallet.get()))
                            .message("User registered successfully!")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build()
            );
        }
        catch (RuntimeException err) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(err.getMessage())
                            .build()
            );
        }
    }

    @PostMapping("/api/users/add_money/{id}")
    public ResponseEntity<Response> addMoneyToWallet(@PathVariable("id") Long userId,
                                                     @RequestBody WalletRequest request) {

        try {
            Optional<Wallet> o_wallet = walletService.addMoneyToWallet(userId, request.getAmount());

            if (o_wallet.isEmpty()) {
                throw new WalletNotFoundException("Wallet not found!");
            }

            return ResponseEntity.ok(
                    Response.builder().timeStamp(LocalDateTime.now())
                            .data(of("wallet", o_wallet.get()))
                            .message("You added money to your wallet successfully!")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        }
        catch (RuntimeException err) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(err.getMessage())
                            .build()
            );
        }
    }

    @PostMapping("/api/users/withdraw_money/{id}")
    public ResponseEntity<Response> withdrawMoneyToWallet(@PathVariable("id") Long userId,
                                                          @RequestBody WalletRequest request) {

        try {
            Optional<Wallet> o_wallet = walletService.withdrawMoneyFromWallet(userId, request.getAmount());

            if (o_wallet.isEmpty()) {
                throw new WalletNotFoundException("Wallet not found!");
            }

            return ResponseEntity.ok(
                    Response.builder().timeStamp(LocalDateTime.now())
                            .data(of("wallet", o_wallet.get()))
                            .message("You withdrawn money to your wallet successfully!")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        }
        catch (RuntimeException err) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(err.getMessage())
                            .build()
            );
        }
    }
}
