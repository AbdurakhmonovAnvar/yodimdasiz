package com.yodimdasiz.yodimdasiz.auth;

import com.yodimdasiz.yodimdasiz.config.JwtService;
import com.yodimdasiz.yodimdasiz.enums.Role;
import com.yodimdasiz.yodimdasiz.exception.BadRequest;
import com.yodimdasiz.yodimdasiz.model.Users;
import com.yodimdasiz.yodimdasiz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class AuthenticationService {



    @Autowired
    private JavaMailSender mailSender;

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public String register(RegisterRequest request, String verCode){
        var user  = Users.builder()
                .username(request.getUsername())
                .name(request.getUsername())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(request.getPhone())
                .verifyCode(verCode)
                .role(Role.USER)
                .isVerified(false)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        return "Verification code send your email. Check your email, please.";
    }

    public AuthenticationResponse verificationCode(String code,String email){
        Optional<Users> optional = repository.findByVerifyCodeAndEmail(code,email);
        if (optional.isEmpty()){
            throw new BadRequest("User not found");
        }
        var user = optional.get();
        user.setVerified(true);
        user.setVerifyCode(null);
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

        return AuthenticationResponse.builder().token(jwtToken).build();

    }


    public String sendVerCode(String email){
        String verificationCode = generateRandomCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verification Code");
        message.setText("Verification code is: " + verificationCode);
        mailSender.send(message);
        return verificationCode;
    }

    private String generateRandomCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000)); // 6-digit code
    }



}
