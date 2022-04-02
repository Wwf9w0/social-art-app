package com.twitter.service.controller;

import com.twitter.service.persistence.jpa.dto.UserDto;
import com.twitter.service.persistence.jpa.request.UserRequest;
import com.twitter.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.saveUser(request));
    }

    @GetMapping()
    public ResponseEntity<UserDto> getUserByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    @PatchMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<HttpStatus> addFollower(@PathVariable String userId) {
        userService.addFollower(userId, UUID.randomUUID().toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/following")
    public ResponseEntity<HttpStatus> addFollowing(@PathVariable String userId) {
        userService.addFollowing(userId, UUID.randomUUID().toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/follow")
    public ResponseEntity<HttpStatus> removeFollower(@PathVariable String userId) {
        userService.removeFollower(userId, UUID.randomUUID().toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<Map<String, Date>> getFollowers(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getFollowers(userId));
    }

    @GetMapping("/{userId}/followings")
    public ResponseEntity<Map<String, Date>> getFollowings(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getFollowings(userId));
    }
}
