package org.example.osahaneatspringboot.repository;

import java.util.Optional;

import org.example.osahaneatspringboot.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRepository extends JpaRepository<Profile, String> {
    boolean existsByEmail(String email);

    Optional<Profile> findByAccountUsername(String username);
}
