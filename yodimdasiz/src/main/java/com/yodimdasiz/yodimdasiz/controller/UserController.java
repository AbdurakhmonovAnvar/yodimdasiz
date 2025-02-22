package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.config.JwtService;
import com.yodimdasiz.yodimdasiz.model.Users;
import com.yodimdasiz.yodimdasiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private JwtService jwtUtil;

    @Autowired
    private UserService service;

    @PutMapping("/phone")
    public ResponseEntity<?> updateUserPhone(@RequestHeader("Authorization") String token, @RequestBody Users user){
        String jwtToken = token.substring(7);
        System.out.println(jwtToken);
        Integer id = jwtUtil.extractUserId(jwtToken);
        Users result = service.updateUserPhone(id,user);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users newUser = service.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        Users user = service.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<Users> updateUser(@RequestHeader("Authorization") String token, @RequestBody Users user) {
        String jwtToken = token.substring(7);
        Integer id = jwtUtil.extractUserId(jwtToken);
        Users updatedUser = service.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestHeader("Authorization") String token, @RequestBody Users user) {
        String jwtToken = token.substring(7);
        Integer id = jwtUtil.extractUserId(jwtToken);
        service.deleteUser(id, user.getEmail(), user.getPassword());
        return ResponseEntity.ok("User successfully deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
