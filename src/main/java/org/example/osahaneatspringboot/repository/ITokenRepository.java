package org.example.osahaneatspringboot.repository;

import java.util.Optional;

import org.example.osahaneatspringboot.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenRepository extends JpaRepository<Token, String> {
    Optional<Token> findByToken(String token);
}
