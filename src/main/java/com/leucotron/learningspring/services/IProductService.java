package com.leucotron.learningspring.services;

import com.fasterxml.jackson.databind.util.JSONPObject;
import java.util.List;
import java.util.Optional;

import com.leucotron.learningspring.entities.JProduct;

public interface IProductService {
    
    public List<JProduct> list();
    
    public Optional<JProduct> find(Long id);
    
    public String store(JProduct product);
    
    public String delete(Long id);
    
//    public String update(Long id, JProduct newProduct);
    
}
