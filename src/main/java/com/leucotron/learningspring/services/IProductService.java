package com.leucotron.learningspring.services;

import java.util.List;
import java.util.Optional;

import com.leucotron.learningspring.entities.JProduct;

/**
 *
 * @author flavio
 */
public interface IProductService {
    
    public List<JProduct> list();
    
    public Optional<JProduct> find(Long id);
    
    public JProduct store(JProduct product);
    
    public String delete(Long id);
    
}
