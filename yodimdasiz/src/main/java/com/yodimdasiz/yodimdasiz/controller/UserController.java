package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.config.JwtService;
import com.yodimdasiz.yodimdasiz.model.Users;
import com.yodimdasiz.yodimdasiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

//    private static final String UPLOAD_USER_DIR = "src/main/resources/static/uploads/";

    @Autowired
    private JwtService jwtUtil;

    @Autowired
    private UserService service;



    @GetMapping
    public ResponseEntity<Users> getUserById(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        Integer id = jwtUtil.extractUserId(jwtToken);
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

    @PutMapping("/uploadPhoto")
    public ResponseEntity<?> uploadImage(@RequestHeader("Authorization") String token,@RequestParam("image")MultipartFile file){
        String jwtToken = token.substring(7);
        Integer id = jwtUtil.extractUserId(jwtToken);
        String uploadImage = service.uploadImage(id,file);
        return ResponseEntity.ok().body(uploadImage);
    }

    @GetMapping("/photo")
    public ResponseEntity<Resource> getUserPhoto(@RequestHeader("Authorization") String token) throws IOException {
        String jwtToken = token.substring(7);
        Integer id = jwtUtil.extractUserId(jwtToken);
        return service.getUserPhoto(id,token);
    }


    @PutMapping
    public ResponseEntity<?> updateName(@RequestHeader("Authorization") String token, @RequestBody Users users){
        String jwtToken = token.substring(7);
        Integer id = jwtUtil.extractUserId(jwtToken);
        Users reslut = service.updateName(id,users);
        return ResponseEntity.ok().body(reslut);

    }





}
