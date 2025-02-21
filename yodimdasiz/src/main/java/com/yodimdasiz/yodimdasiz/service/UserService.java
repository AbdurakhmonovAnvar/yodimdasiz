package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.exception.BadRequest;
import com.yodimdasiz.yodimdasiz.model.Users;
import com.yodimdasiz.yodimdasiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users updateUserPhone(Integer id, Users users){
        Optional<Users> optional = repository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("User not found");
        }
        Users user = optional.get();
        user.setPhone(users.getPhone());
        return repository.save(user);
    }

}
