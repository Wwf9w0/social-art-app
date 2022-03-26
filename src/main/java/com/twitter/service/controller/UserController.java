package com.twitter.service.controller;

import com.twitter.service.persistence.jpa.dto.UserDto;
import com.twitter.service.persistence.jpa.request.UserRequest;
import com.twitter.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserRequest request) {
        userService.saveUser(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserByUserId(id));
    }

    @PatchMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<HttpStatus> addFollower(@PathVariable String userId{
        userService.addFollower(userId, UUID.randomUUID().toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addFollowing(@PathVariable String userId){

    }

    @DeleteMapping("/{userId}/follow")
    public ResponseEntity<HttpStatus> removeFollower(@PathVariable String userId){
        userService.removeFollower(userId, UUID.randomUUID().toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<Map<String, Date>> getFollowers(@PathVariable String userId){
        return ResponseEntity.ok(userService.getFollowers(userId));
    }

    @GetMapping("/{userId}/followings")
    public ResponseEntity<Map<String, Date>> getFollowings(@PathVariable String userId){
        return ResponseEntity.ok(userService.getFollowings(userId));
    }


}
