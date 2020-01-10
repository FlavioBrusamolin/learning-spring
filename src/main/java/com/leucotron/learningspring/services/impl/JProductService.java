package com.leucotron.learningspring.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.leucotron.learningspring.entities.JProduct;
import com.leucotron.learningspring.repositories.IProductRepository;
import com.leucotron.learningspring.services.IProductService;

@Service
public class JProductService implements IProductService {

    @Autowired
    IProductRepository productRepository;

    @Override
    public List<JProduct> list() {
        return productRepository.findAll();
    }

    @Override
    public Optional<JProduct> find(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public JProduct store(JProduct product) {
        return productRepository.save(product);
    }

    @Override
    public String delete(Long id) {
        productRepository.deleteById(id);
        return "Successful deletion";
    }

}
