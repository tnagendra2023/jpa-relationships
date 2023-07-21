package com.java.jpa.controller;

import com.java.jpa.dto.UserRequestDTO;
import com.java.jpa.dto.UserResponseDTO;
import com.java.jpa.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * This controller performs @OneToMany Bidirectional JPA mapping
 *
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(userService.addUser(userRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long carId){
        userService.deleteUser(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsersAndAddress(){
        return new ResponseEntity<>(userService.fetchAllUsersAndAddresses(),HttpStatus.OK);
    }

}
