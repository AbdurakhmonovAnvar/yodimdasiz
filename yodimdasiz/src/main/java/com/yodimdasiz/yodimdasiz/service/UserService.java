package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.config.JwtService;
import com.yodimdasiz.yodimdasiz.exception.BadRequest;
import com.yodimdasiz.yodimdasiz.model.Users;
import com.yodimdasiz.yodimdasiz.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users updateUserPhone(Integer id, Users users) {
        Optional<Users> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequest("User not found");
        }
        Users user = optional.get();
        user.setPhone(users.getPhone());
        return repository.save(user);
    }


    public Users getUserById(Integer id) {
        Optional<Users> optional = repository.findById(id);
        if (optional.isEmpty()) {
            var strId = String.valueOf(id);
            throw new BadRequest("User" + strId + "not found");
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
        if (optional.isEmpty()) {
            throw new BadRequest("User not found");
        }
        var user = optional.get();
        repository.delete(user);
    }

    public List<Users> getAllUsers() {
        return repository.findAll();
    }

    public Users updatePassword(Integer id, Users passwordUser) {
        Users user = repository.findById(id).orElseThrow(() -> new BadRequest("User not found"));
        user.setPassword(passwordEncoder.encode(passwordUser.getPassword()));
        return repository.save(user);
    }


    public Users updateRole(Integer id, Users roleUser) {
        Users user = repository.findById(id).orElseThrow(() -> new BadRequest("User not found"));
        user.setRole(roleUser.getRole());
        return repository.save(user);
    }

    public String uploadImage(Integer id, MultipartFile file) {
        String YMD = getYMD();
        String token = UUID.randomUUID().toString();
        Optional<Users> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new BadRequest("User not found");
        }

        var user = optional.get();

        String uploadDir = new File("yodimdasiz/src/main/resources/static/uploads/users").getAbsolutePath();
        File folder = new File(uploadDir + File.separator + YMD);
        if (!folder.exists() && !folder.mkdirs()) {
            throw new RuntimeException("Failed to create directory: " + folder.getAbsolutePath());
        }

        String filePath = folder.getAbsolutePath() + File.separator + token + ".png";
        File imageFile = new File(filePath);

        if (file == null || file.isEmpty()) {
            throw new BadRequest("File is empty");
        }

        try {
                file.transferTo(imageFile);
            user.setPhotoUrl("/uploads/users/" + YMD + "/" + token + ".png");
            repository.save(user);
            return "File uploaded successfully: " + user.getPhotoUrl();
        } catch (IOException e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }


    public String getYMD() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return String.format("%s/%s/%s", year, month + 1, day);
    }
    private final String uploadDir = "yodimdasiz/src/main/resources/static/uploads/users/";

    public ResponseEntity<Resource> getUserPhoto(Integer id, String token) throws IOException {
        Optional<Users> optional = repository.findById(id);

        if (optional.isEmpty() || optional.get().getPhotoUrl() == null) {
            return ResponseEntity.notFound().build();
        }

        String photoUrl = optional.get().getPhotoUrl();
        Path filePath = Paths.get(uploadDir).resolve(photoUrl.replace("/uploads/users/", "")).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }


    public Users updateName(Integer id, Users users){
        Optional<Users> optional = repository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("User not found");
        }
        var user = optional.get();
        user.setName(users.getName());
        return repository.save(user);

    }



}
