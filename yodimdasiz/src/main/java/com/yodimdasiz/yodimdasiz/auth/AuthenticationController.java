package com.yodimdasiz.yodimdasiz.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        String verCode = authService.sendVerCode(request.getEmail());
        return ResponseEntity.ok(authService.register(request,verCode));
    }

    @PostMapping("/verCode")
    public ResponseEntity<AuthenticationResponse> verificationCode(@RequestParam String code, @RequestParam String email){
        AuthenticationResponse verCode = authService.verificationCode(code,email);
        return ResponseEntity.ok().body(verCode);
    }



    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }


}
