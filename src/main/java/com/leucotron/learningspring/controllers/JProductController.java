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
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.leucotron.learningspring.entities.JProduct;
import com.leucotron.learningspring.services.IProductService;

/**
 *
 * @author flavio
 */
@RestController
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class JProductController {

    @Autowired
    IProductService productService;

    @GetMapping()
    public ResponseEntity<List<JProduct>> list() {
        return ResponseEntity.ok(productService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable(value = "id") Long id) {
        Optional<JProduct> product = productService.find(id);

        if (!product.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Product not found");
        }

        return ResponseEntity.ok(product.get());
    }

    @PostMapping()
    public ResponseEntity<JProduct> store(@RequestBody JProduct product) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.store(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        if (!productService.find(id).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Product not found");
        }

        return ResponseEntity.ok(productService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody JProduct product) {
        if (!productService.find(id).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Product not found");
        }

        product.setId(id);
        productService.store(product);

        return ResponseEntity.ok(product);
    }

}
