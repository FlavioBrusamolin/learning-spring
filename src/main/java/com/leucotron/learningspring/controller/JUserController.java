package com.leucotron.learningspring.controller;

import com.leucotron.learningspring.dto.JUserDTO;
import com.leucotron.learningspring.response.JSuccessResponse;
import com.leucotron.learningspring.service.IUserService;
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
@Api(tags = {"User"})
@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class JUserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "Returns registered users.")
    @GetMapping()
    public ResponseEntity<JSuccessResponse> list() {
        List<JUserDTO> data = userService.list();
        JSuccessResponse response = new JSuccessResponse("Users found", data);
        return buildResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns a user by id.")
    @GetMapping("/{id}")
    public ResponseEntity<JSuccessResponse> find(@PathVariable(value = "id") Long id) {
        JUserDTO data = userService.find(id);
        JSuccessResponse response = new JSuccessResponse("User found", data);
        return buildResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new user.")
    @PostMapping()
    public ResponseEntity<JSuccessResponse> store(
            @ApiParam(name = "User", value = "User data.") @Valid @RequestBody JUserDTO dto) {
        
        JUserDTO data = userService.store(dto);
        JSuccessResponse response = new JSuccessResponse("Successfully created user", data);
        return buildResponseEntity(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete a user by id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<JSuccessResponse> delete(@PathVariable(value = "id") Long id) {
        JUserDTO data = userService.delete(id);
        JSuccessResponse response = new JSuccessResponse("Successfully deleted user", data);
        return buildResponseEntity(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Update a user by id.")
    @PutMapping("/{id}")
    public ResponseEntity<JSuccessResponse> update(
            @PathVariable(value = "id") Long id,
            @ApiParam(name = "User", value = "New user data.") @Valid @RequestBody JUserDTO dto) {
        
        JUserDTO data = userService.update(id, dto);
        JSuccessResponse response = new JSuccessResponse("Successfully updated user", data);
        return buildResponseEntity(response, HttpStatus.OK);
    }

    private ResponseEntity<JSuccessResponse> buildResponseEntity(JSuccessResponse response, HttpStatus status) {
        return ResponseEntity.status(status).body(response);
    }

}
