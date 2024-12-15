package org.example.osahaneatspringboot.repository;

import org.example.osahaneatspringboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, String> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, String id);
}
