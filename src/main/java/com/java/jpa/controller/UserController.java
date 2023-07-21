package com.java.jpa.controller;

import com.java.jpa.dto.UserDTORequest;
import com.java.jpa.dto.UserDTOResponse;
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
    public ResponseEntity<UserDTOResponse> addUser(@RequestBody UserDTORequest userDTORequest){
        return new ResponseEntity<>(userService.addUser(userDTORequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long carId){
        userService.deleteUser(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> getAllUsersAndAddress(){
        return new ResponseEntity<>(userService.fetchAllUsersAndAddresses(),HttpStatus.OK);
    }

}
