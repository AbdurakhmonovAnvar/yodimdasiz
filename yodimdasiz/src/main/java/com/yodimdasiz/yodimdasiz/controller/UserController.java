package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.config.JwtService;
import com.yodimdasiz.yodimdasiz.model.Users;
import com.yodimdasiz.yodimdasiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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

}
