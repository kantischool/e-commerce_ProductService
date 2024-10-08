package com.example.Product.repository;

import com.example.Product.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    @Override
    <S extends Category> S save(S entity);
}
