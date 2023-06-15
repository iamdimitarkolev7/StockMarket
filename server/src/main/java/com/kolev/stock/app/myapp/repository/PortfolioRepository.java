package com.kolev.stock.app.myapp.repository;

import com.kolev.stock.app.myapp.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    @Override
    <S extends Portfolio> S save(S wallet);

    @Override
    Optional<Portfolio> findById(Long id);
}
