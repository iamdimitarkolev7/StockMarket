package com.kolev.stock.app.myapp.repository;

import com.kolev.stock.app.myapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    <S extends User> S save(S entity);

    @Override
    boolean existsById(Long id);

    @Override
    Optional<User> findById(Long id);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
