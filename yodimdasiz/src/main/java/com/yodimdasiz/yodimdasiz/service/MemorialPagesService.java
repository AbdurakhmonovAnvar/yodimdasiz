package com.yodimdasiz.yodimdasiz.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.yodimdasiz.yodimdasiz.exception.BadRequest;
import com.yodimdasiz.yodimdasiz.exception.NotFoundException;
import com.yodimdasiz.yodimdasiz.model.MemorialPages;
import com.yodimdasiz.yodimdasiz.model.Users;
import com.yodimdasiz.yodimdasiz.repository.MemorialPagesRepository;
import com.yodimdasiz.yodimdasiz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;


import javax.swing.text.html.Option;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemorialPagesService {

    @Autowired
    private MemorialPagesRepository repository;

    @Autowired
    private UserRepository userRepository;

    private final String qrCodeDIR = "yodimdasiz/src/main/resources/static/uploads/qrCode/";
    private final String uploadDir = "yodimdasiz/src/main/resources/static/uploads/memorial/";




    public MemorialPages createMemorialPage(Integer id, MemorialPages memorialPage) {

        Optional<Users> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequest("User not found");
        }
        var user = optional.get();
        memorialPage.setUserCreatorId(user);
        memorialPage.setCreatedAt(LocalDateTime.now());
        return repository.save(memorialPage);
    }

    public MemorialPages uploadImage(Integer id, MultipartFile file) {
        Optional<MemorialPages> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequest("Memorial not found");
        }
        var memorialPage = optional.get();
        if (!file.isEmpty()) {
            String YMD = getYMD();
            String token = UUID.randomUUID().toString();
            String uploadDir = new File("yodimdasiz/src/main/resources/static/uploads/memorial").getAbsolutePath();
            File folder = new File(uploadDir + File.separator + YMD);
            if (!folder.exists() && !folder.mkdirs()) {
                throw new RuntimeException("Failed to create directory: " + folder.getAbsolutePath());
            }
            String filePath = folder.getAbsolutePath() + File.separator + token + ".png";
            File imageFile = new File(filePath);
            try {
                file.transferTo(imageFile);
                memorialPage.setMainPhoto("/uploads/memorial/" + YMD + "/" + token + ".png");
                return repository.save(memorialPage);
            } catch (IOException e) {
                throw new RuntimeException("File upload failed: " + e.getMessage());
            }
        }
        return repository.save(memorialPage);
    }

    public MemorialPages generateQRCode(Integer id) {
        String text = "http://localhost:8080/api/v1/memorep/memor/"+id.toString();
        Optional<MemorialPages> optional = repository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("Memorial not found");
        }
        var memorial = optional.get();
        try {
            int width = 500;
            int height = 500;
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);

            String YMD = getYMD();
            String token = UUID.randomUUID().toString();
            File folder = new File(qrCodeDIR, YMD);
            if (!folder.exists() && !folder.mkdirs()) {
                throw new RuntimeException("Failed to create directory: " + folder.getAbsolutePath());
            }

            File qrFile = new File(folder, token + ".png");
            Path path = qrFile.toPath();

            if (!qrFile.exists() && !qrFile.createNewFile()) {
                throw new RuntimeException("Failed to create QR code file: " + qrFile.getAbsolutePath());
            }
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            memorial.setQrCode("/uploads/qrCode/"+YMD+"/"+token+".png");
            return  repository.save(memorial);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public MemorialPages getMemorial(Integer id){
        Optional<MemorialPages> optional = repository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("Memorial not found");
        }
        var memor = optional.get();
        return memor;
    }

    public ResponseEntity<Resource> getMemorialPhoto(Integer id) throws IOException{
        Optional<MemorialPages> optional = repository.findById(id);

        if (optional.isEmpty() || optional.get().getMainPhoto() == null) {
            return ResponseEntity.notFound().build();
        }
        String photoUrl = optional.get().getMainPhoto();
        Path filePath = Paths.get(uploadDir).resolve(photoUrl.replace("/uploads/memorial/", "")).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        String contentType = Files.probeContentType(filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);

    }

    public ResponseEntity<Resource> getMemorialQRCodePhoto(Integer id) throws IOException{
        Optional<MemorialPages> optional = repository.findById(id);
        if (optional.isEmpty() || optional.get().getQrCode() == null) {
            return ResponseEntity.notFound().build();
        }
        String photoUrl = optional.get().getQrCode();
        Path filePath = Paths.get(qrCodeDIR).resolve(photoUrl.replace("/uploads/qrCode/", "")).normalize();
        Resource resource = new UrlResource(filePath.toUri());


        String contentType = Files.probeContentType(filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);

    }

    public String getYMD() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        return String.format("%s/%s/%s", year, month + 1, day);
    }



}
