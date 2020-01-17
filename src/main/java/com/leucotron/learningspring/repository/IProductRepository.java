package com.leucotron.learningspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.leucotron.learningspring.entity.JProduct;

/**
 *
 * @author flavio
 */
public interface IProductRepository extends JpaRepository<JProduct, Long> {
    
    public Optional<JProduct> findByName(String name);
    
}
