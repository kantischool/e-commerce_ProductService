package com.example.Product.repository;

import com.example.Product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);

   Optional<Product> findByName(String name);

   Optional<List<Product>> findByPriceGreaterThan(int price);

   @Query("select p from Product p where p.id = :id")
   Product fetchProduct(@Param("id") Long id);

    @Query("select p.price as price, p.image as url from Product p where p.id = :id")
    ProductWithPriceAndUrl fetchSpecificDetail(@Param("id") Long id);

    @Query(value = "select * from product where id = :id", nativeQuery = true)
    Product productWithNativeQuery(@Param("id") Long id);
}
