package com.kolev.stock.app.myapp.repository;

import com.kolev.stock.app.myapp.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Override
    <S extends Wallet> S save(S wallet);

    @Override
    Optional<Wallet> findById(Long id);
}
