package com.leucotron.learningspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leucotron.learningspring.entities.JProduct;

public interface IProductRepository extends JpaRepository<JProduct, Long> {
}
