package com.leucotron.learningspring.repository;

import com.leucotron.learningspring.entity.JProduct;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author flavio
 */
public interface IProductRepository extends JpaRepository<JProduct, Long> {
    
    public Optional<JProduct> findByName(String name);
    
}
