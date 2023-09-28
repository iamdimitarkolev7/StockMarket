package com.kolev.stock.app.myapp.service.implementations;

import com.kolev.stock.app.myapp.exceptions.users.UserDoesNotExistException;
import com.kolev.stock.app.myapp.models.Transaction;
import com.kolev.stock.app.myapp.models.User;
import com.kolev.stock.app.myapp.repository.TransactionRepository;
import com.kolev.stock.app.myapp.repository.UserRepository;
import com.kolev.stock.app.myapp.service.interfaces.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public List<Transaction> getAllTransactions(Long userId) {
        Optional<User> o_user = userRepository.findById(userId);

        if (o_user.isEmpty()) {
            return null;
        }

        User user = o_user.get();

        List<Transaction> transactions = user.getTransactions();

        return transactions;
    }
}
