package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.exception.BadRequest;
import com.yodimdasiz.yodimdasiz.model.Users;
import com.yodimdasiz.yodimdasiz.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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


    public Users updateRole(Integer id, Users roleUser){
        Users user = repository.findById(id).orElseThrow(() -> new BadRequest("User not found"));
        user.setRole(roleUser.getRole());
        return repository.save(user);
    }

    public String uploadImage(Integer id, String UPLOAD_USER_DIR, MultipartFile file){
        try {
            LocalDate today = LocalDate.now();
            String year = String.valueOf(today.getYear());
            String month = String.format("%02d",today.getMonthValue());
            String day =  String.format("%02d",today.getDayOfMonth());

            String userFolderPath = UPLOAD_USER_DIR+ year+"/"+month+"/"+day+"users";
            File userFolder = new File(userFolderPath);
            if (!userFolder.exists()) userFolder.mkdir();

            String fileName = UUID.randomUUID()+"_"+file.getOriginalFilename();
            Path filePath = Paths.get(userFolderPath+fileName);

            Files.write(filePath,file.getBytes());

            String fileUrl =  "/uploads/" + year + "/" + month + "/" + day + "/users/" + fileName;

            return "File uploaded successfully: " + fileUrl;
        }
        catch (IOException e) {
            return "File upload failed: " + e.getMessage();
        }

    }



}
