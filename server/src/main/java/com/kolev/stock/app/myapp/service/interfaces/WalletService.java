package com.kolev.stock.app.myapp.service.interfaces;

import com.kolev.stock.app.myapp.models.Wallet;

import java.util.Optional;

public interface WalletService {

    Optional<Wallet> getWalletByUserId(Long userId);

    Optional<Wallet> addMoneyToWallet(Long walletId, Long amount);

    Optional<Wallet> withdrawMoneyFromWallet(Long walletId, Long amount);
}
