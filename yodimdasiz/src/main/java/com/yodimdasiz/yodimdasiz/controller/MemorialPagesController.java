package com.yodimdasiz.yodimdasiz.controller;

import com.yodimdasiz.yodimdasiz.config.JwtService;
import com.yodimdasiz.yodimdasiz.model.MemorialPages;
import com.yodimdasiz.yodimdasiz.service.MemorialPagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/memorep")
@RequiredArgsConstructor
public class MemorialPagesController {

    @Autowired
    private  MemorialPagesService service;

    @Autowired
    private JwtService jwtUtil;



    @PostMapping("/create")
    public ResponseEntity<MemorialPages> createMemorialPage(@RequestHeader("Authorization") String token, @RequestBody MemorialPages memorialPage ) {
        String jwtToken = token.substring(7);
        Integer userId = jwtUtil.extractUserId(jwtToken);
        MemorialPages result = service.createMemorialPage(userId,memorialPage);
        return ResponseEntity.ok().body(result);
    }


    @PutMapping("/upload/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable Integer id, @RequestParam("image") MultipartFile file){
        MemorialPages result = service.uploadImage(id, file);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping("/generateQR/{id}")
    public ResponseEntity<?> generateQRCode(@PathVariable("id") Integer id){
        MemorialPages result = service.generateQRCode(id);
        return ResponseEntity.ok().body("QrCode created successfully");
    }

    @GetMapping("/memor/{id}")
    public ResponseEntity<?> getMemorial(@PathVariable("id") Integer id){
        MemorialPages result = service.getMemorial(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/memor/image/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") Integer id) throws IOException {
        return service.getMemorialPhoto(id);

    }

    @GetMapping("/memor/qrCode/{id}")
    public ResponseEntity<?> getqQRCodeImage(@PathVariable("id") Integer id) throws IOException{
        return service.getMemorialQRCodePhoto(id);
    }



}
