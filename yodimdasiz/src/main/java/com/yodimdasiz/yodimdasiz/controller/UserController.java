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

//    @PutMapping("/phone")
//    public ResponseEntity<?> updateUserPhone(@RequestHeader("Authorization") String token, @RequestBody Users user){
//        String jwtToken = token.substring(7);
//        System.out.println(jwtToken);
//        Integer id = jwtUtil.extractUserId(jwtToken);
//        Users result = service.updateUserPhone(id,user);
//        return ResponseEntity.ok(result);
//    }


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
    @PutMapping("/username")
    public ResponseEntity<Users> updateName(@RequestHeader("Authorization") String token, @RequestBody Users user) {
        String jwtToken = token.substring(7);
        Integer id = jwtUtil.extractUserId(jwtToken);
        Users updateName = service.updateName(id, user);
        return ResponseEntity.ok(updateName);
    }
    @PutMapping("/email")
    public ResponseEntity<Users> updateEmail(@RequestHeader("Authorization") String token, @RequestBody Users user) {
        String jwtToken = token.substring(7);
        Integer id = jwtUtil.extractUserId(jwtToken);
        Users updateEmail = service.updateEmail(id, user);
        return ResponseEntity.ok(updateEmail);
    }
    @PutMapping("/role")
    public ResponseEntity<Users> updateRole(@RequestHeader("Authorization") String token, @RequestBody Users user) {
        String jwtToken = token.substring(7);
        Integer id = jwtUtil.extractUserId(jwtToken);
        Users updateRole = service.updateRole(id, user);
        return ResponseEntity.ok(updateRole);
    }
    @PutMapping("/phone")
    public ResponseEntity<Users> updatePhone(@RequestHeader("Authorization") String token, @RequestBody Users user) {
        String jwtToken = token.substring(7);
        Integer id = jwtUtil.extractUserId(jwtToken);
        Users updatedPhone = service.updateUserPhone(id, user);
        return ResponseEntity.ok(updatedPhone);
    }



    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestHeader("Authorization") String token,@RequestBody Users user){
        String jwtToken = token.substring(7);
        Integer id = jwtUtil.extractUserId(jwtToken);
        Users updatedPassword = service.updatePassword(id, user);
        return ResponseEntity.ok(updatedPassword);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        Integer id = jwtUtil.extractUserId(jwtToken);
        service.deleteUser(id);
        return ResponseEntity.ok("User successfully deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
