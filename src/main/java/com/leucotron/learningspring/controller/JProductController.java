package com.leucotron.learningspring.controller;

import java.util.List;

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

import javax.validation.Valid;

import com.leucotron.learningspring.service.IProductService;
import com.leucotron.learningspring.dto.JProductDTO;
import com.leucotron.learningspring.response.JSuccessResponse;

/**
 *
 * @author flavio
 */
@RestController
@RequestMapping(value = "/api/v1/products")
public class JProductController {

    @Autowired
    private IProductService productService;

    @GetMapping()
    public ResponseEntity<JSuccessResponse> list() {
        List<JProductDTO> data = productService.list();
        JSuccessResponse response = new JSuccessResponse("Successfully found products", data);
        return buildResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JSuccessResponse> find(@PathVariable(value = "id") Long id) {
        JProductDTO data = productService.find(id);
        JSuccessResponse response = new JSuccessResponse("Successfully found product", data);
        return buildResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<JSuccessResponse> store(@Valid @RequestBody JProductDTO dto) {
        JProductDTO data = productService.store(dto);
        JSuccessResponse response = new JSuccessResponse("Successfully created product", data);
        return buildResponseEntity(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JSuccessResponse> delete(@PathVariable(value = "id") Long id) {
        JProductDTO data = productService.delete(id);
        JSuccessResponse response = new JSuccessResponse("Successfully deleted product", data);
        return buildResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JSuccessResponse> update(@PathVariable(value = "id") Long id, @Valid @RequestBody JProductDTO dto) {
        JProductDTO data = productService.update(id, dto);
        JSuccessResponse response = new JSuccessResponse("Successfully updated product", data);
        return buildResponseEntity(response, HttpStatus.OK);
    }

    private ResponseEntity<JSuccessResponse> buildResponseEntity(JSuccessResponse response, HttpStatus status) {
        return ResponseEntity.status(status).body(response);
    }

}
