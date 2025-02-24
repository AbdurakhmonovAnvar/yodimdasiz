package com.yodimdasiz.yodimdasiz.auth;

import com.yodimdasiz.yodimdasiz.config.JwtService;
import com.yodimdasiz.yodimdasiz.enums.Role;
import com.yodimdasiz.yodimdasiz.exception.BadRequest;
import com.yodimdasiz.yodimdasiz.model.Users;
import com.yodimdasiz.yodimdasiz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var user  = Users.builder()
                .username(request.getUsername())
                .name(request.getUsername())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(request.getPhone())
                .role(Role.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        var jwtToken = jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        if ((request.getEmail() == null || request.getEmail().isBlank()) &&
                (request.getPhone() == null || request.getPhone().isBlank())){
                throw new BadRequest("Phone number or email is mandatory");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail() != null ? request.getEmail() : request.getPhone(), request.getPassword()
        ));

        var user = repository.findByEmail(request.getEmail())
                        .or(() -> repository.findByPhone(request.getPhone()))
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        var jwtToken = jwtService.generateToken(user, user.getId());


//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//        var user = repository.findByEmail(request.getEmail()).orElseThrow();
//        var jwtToken = jwtService.generateToken(user, user.getId());
        return AuthenticationResponse.builder().token(jwtToken).build();

    }

//    public AuthenticationResponse authPhone(AuthenticationRequest request) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPhone(), request.getPassword()));
//        var user = repository.findByPhone(request.getPhone()).orElseThrow();
//        var jwtToken = jwtService.generateToken(user, user.getId());
//        return AuthenticationResponse.builder().token(jwtToken).build();
//
//    }

}
