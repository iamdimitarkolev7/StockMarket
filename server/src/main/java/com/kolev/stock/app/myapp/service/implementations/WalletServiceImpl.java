package com.kolev.stock.app.myapp.service.implementations;

import com.kolev.stock.app.myapp.enums.TransactionType;
import com.kolev.stock.app.myapp.exceptions.users.UserDoesNotExistException;
import com.kolev.stock.app.myapp.exceptions.wallets.NotEnoughMoneyException;
import com.kolev.stock.app.myapp.exceptions.wallets.WalletNotFoundException;
import com.kolev.stock.app.myapp.models.Transaction;
import com.kolev.stock.app.myapp.models.User;
import com.kolev.stock.app.myapp.models.Wallet;
import com.kolev.stock.app.myapp.repository.TransactionRepository;
import com.kolev.stock.app.myapp.repository.UserRepository;
import com.kolev.stock.app.myapp.repository.WalletRepository;
import com.kolev.stock.app.myapp.service.interfaces.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Optional<Wallet> getWalletByUserId(Long userId) {

        User user = getUserById(userId);
        Wallet wallet = user.getUserWallet();
        Long walletId = wallet.getWalletId();

        return walletRepository.findById(walletId);
    }

    @Override
    public Optional<Wallet> addMoneyToWallet(Long userId, Long amount) {

        User user = getUserById(userId);
        Wallet wallet = user.getUserWallet();
        wallet.setAvailableBalance(wallet.getAvailableBalance() + amount);
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.ADD_MONEY)
                .value(amount)
                .wallet(wallet)
                .build();
        Transaction newTransaction = transactionRepository.save(transaction);
        wallet.addTransaction(newTransaction);

        return Optional.ofNullable(walletRepository.save(wallet));
    }

    @Override
    @PreAuthorize("permitAll()")
    public Optional<Wallet> withdrawMoneyFromWallet(Long userId, Long amount) {

        User user = getUserById(userId);
        Wallet wallet = user.getUserWallet();

        if (wallet.getAvailableBalance() - amount < 0) {
            throw new NotEnoughMoneyException("Cannot withdraw that amount of money!");
        }

        wallet.setAvailableBalance(wallet.getAvailableBalance() - amount);
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.WITHDRAW_MONEY)
                .value(amount)
                .wallet(wallet)
                .build();
        Transaction newTransaction = transactionRepository.save(transaction);
        wallet.addTransaction(newTransaction);

        return Optional.ofNullable(walletRepository.save(wallet));
    }

    private User getUserById(Long userId) {
        Optional<User> o_user = userRepository.findById(userId);

        if (o_user.isEmpty()) {
            throw new UserDoesNotExistException("User with such id doesn't exist");
        }

        return o_user.get();
    }
}
