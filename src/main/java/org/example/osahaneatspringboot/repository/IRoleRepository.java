package org.example.osahaneatspringboot.repository;

import java.util.Optional;

import org.example.osahaneatspringboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByCode(String code);

    boolean existsByCode(String code);
}
