package com.leucotron.learningspring.controllers;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.leucotron.learningspring.entities.JProduct;
import com.leucotron.learningspring.services.IProductService;
import com.leucotron.learningspring.utils.ResponseGenerator;

@RestController
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class JProductController {

    @Autowired
    IProductService productService;

    @GetMapping()
    public List<JProduct> list() {
        return productService.list();
    }

    @GetMapping("/{id}")
    public Optional<JProduct> find(@PathVariable(value = "id") Long id) {
        return productService.find(id);
    }

    @PostMapping()
    public String store(@RequestBody JProduct product) {
        String message = productService.store(product);

        ResponseGenerator responseGenerator = new ResponseGenerator();
        return responseGenerator.setMessage(message);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        String message = productService.delete(id);

        ResponseGenerator responseGenerator = new ResponseGenerator();
        return responseGenerator.setMessage(message);
    }

}
