package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.exception.BadRequest;
import com.yodimdasiz.yodimdasiz.model.Users;
import com.yodimdasiz.yodimdasiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Users createUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public Users getUserById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new BadRequest("User not found"));
    }

    public Users updateUser(Integer id, Users updatedUser) {
        Users user = repository.findById(id).orElseThrow(() -> new BadRequest("User not found"));

        if (updatedUser.getPhone() != null) {
            user.setPhone(updatedUser.getPhone());
        }
        if (updatedUser.getEmail() != null) {
            user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        return repository.save(user);
    }

    public void deleteUser(Integer id, String email, String password) {
        Users user = repository.findById(id).orElseThrow(() -> new BadRequest("User not found"));

        if (!user.getEmail().equals(email) || !passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequest("Invalid credentials");
        }

        repository.delete(user);
    }

    public List<Users> getAllUsers() {
        return repository.findAll();
    }
}
