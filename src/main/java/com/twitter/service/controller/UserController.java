package com.twitter.service.controller;

import com.twitter.service.persistence.jpa.dto.UserDto;
import com.twitter.service.persistence.jpa.request.UserRequest;
import com.twitter.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserRequest request){
        userService.saveUser(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name){
        return ResponseEntity.ok(userService.getUserByName(name));
    }
}
