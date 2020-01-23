package com.leucotron.learningspring.controller;

import com.leucotron.learningspring.dto.JProductDTO;
import com.leucotron.learningspring.response.JSuccessResponse;
import com.leucotron.learningspring.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author flavio
 */
@Api(tags = {"Product"})
@RestController
@RequestMapping(value = "/api/v1/products", produces = "application/json")
public class JProductController {

    @Autowired
    private IProductService productService;

    @ApiOperation(value = "Returns registered products.")
    @GetMapping()
    public ResponseEntity<JSuccessResponse> list() {
        List<JProductDTO> data = productService.list();
        JSuccessResponse response = new JSuccessResponse("Products found", data);
        return buildResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns a product by id.")
    @GetMapping("/{id}")
    public ResponseEntity<JSuccessResponse> find(@PathVariable(value = "id") Long id) {
        JProductDTO data = productService.find(id);
        JSuccessResponse response = new JSuccessResponse("Product found", data);
        return buildResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new product.")
    @PostMapping()
    public ResponseEntity<JSuccessResponse> store(
            @ApiParam(name = "Product", value = "Product data.") @Valid @RequestBody JProductDTO dto) {
        
        JProductDTO data = productService.store(dto);
        JSuccessResponse response = new JSuccessResponse("Successfully created product", data);
        return buildResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a product by id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<JSuccessResponse> delete(@PathVariable(value = "id") Long id) {
        JProductDTO data = productService.delete(id);
        JSuccessResponse response = new JSuccessResponse("Successfully deleted product", data);
        return buildResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Update a product by id.")
    @PutMapping("/{id}")
    public ResponseEntity<JSuccessResponse> update(
            @PathVariable(value = "id") Long id, 
            @ApiParam(name = "Product", value = "New product data.") @Valid @RequestBody JProductDTO dto) {
        
        JProductDTO data = productService.update(id, dto);
        JSuccessResponse response = new JSuccessResponse("Successfully updated product", data);
        return buildResponseEntity(response, HttpStatus.OK);
    }

    private ResponseEntity<JSuccessResponse> buildResponseEntity(JSuccessResponse response, HttpStatus status) {
        return ResponseEntity.status(status).body(response);
    }

}
