package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.exception.BadRequest;
import com.yodimdasiz.yodimdasiz.model.Users;
import com.yodimdasiz.yodimdasiz.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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


    public Users getUserById(Integer id) {
        Optional<Users> optional = repository.findById(id);
        if (optional.isEmpty()){
            var strId = String.valueOf(id);
            throw new BadRequest("User"+strId+"not found");
        }
        return optional.get();
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

    public void deleteUser(Integer id) {
        Optional<Users> optional = repository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("User not found");
        }
        var  user = optional.get();
        repository.delete(user);
    }

    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    public Users updatePassword(Integer id, Users passwordUser){
        Users user = repository.findById(id).orElseThrow(() -> new BadRequest("User not found"));
        user.setPassword(passwordEncoder.encode(passwordUser.getPassword()));
        return repository.save(user);
    }
    public Users updateName(Integer id, Users nameUser){
        Users user = repository.findById(id).orElseThrow(() -> new BadRequest("User not found"));
        user.setUsername(passwordEncoder.encode(nameUser.getUsername()));
        return repository.save(user);
    }
    public Users updateEmail(Integer id, Users emailUser){
        Users user = repository.findById(id).orElseThrow(() -> new BadRequest("User not found"));
        user.setEmail(passwordEncoder.encode(emailUser.getEmail()));
        return repository.save(user);
    }
    public Users updateRole(Integer id, Users roleUser){
        Users user = repository.findById(id).orElseThrow(() -> new BadRequest("User not found"));
        user.setRole(roleUser.getRole());
        return repository.save(user);
    }



}
