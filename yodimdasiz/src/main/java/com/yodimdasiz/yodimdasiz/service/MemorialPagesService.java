package com.yodimdasiz.yodimdasiz.service;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemorialPagesService {

    @Autowired
    private MemorialPagesRepository memorialPagesRepository;

    @Autowired
    private UserRepository userRepository;

//    @Cacheable("memorialPages")
//    public List<MemorialPages> getAllMemorialPages() {
//        return memorialPagesRepository.findAll();
//    }
//
//    public MemorialPages getMemorialPageById(Integer id) {
//        return memorialPagesRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Memorial page not found with ID: " + id));
//    }
//
//    public List<MemorialPages> getMemorialPagesByUserId(Integer userId) {
//        return memorialPagesRepository.getMemorialPagesByUserId(userId);
//    }


    public MemorialPages createMemorialPage(Integer id, MemorialPages memorialPage) {
        Optional<Users> optional = userRepository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequest("User not found");
        }
        var user = optional.get();
        memorialPage.setUserCreatorId(user);
        memorialPage.setCreatedAt(LocalDateTime.now());
        return memorialPagesRepository.save(memorialPage);
    }

//    @Transactional
//    @CacheEvict(value = "memorialPages", allEntries = true)
//    public MemorialPages updateMemorialPage(Integer id, MemorialPages updatedPage) {
//        MemorialPages existingPage = getMemorialPageById(id);
//
//        if (updatedPage.getFullName() != null) existingPage.setFullName(updatedPage.getFullName());
//        if (updatedPage.getBirthDate() != null) existingPage.setBirthDate(updatedPage.getBirthDate());
//        if (updatedPage.getDeathDate() != null) existingPage.setDeathDate(updatedPage.getDeathDate());
//        if (updatedPage.getBiography() != null) existingPage.setBiography(updatedPage.getBiography());
//        if (updatedPage.getEpitaph() != null) existingPage.setEpitaph(updatedPage.getEpitaph());
//        if (updatedPage.getMainPhoto() != null) existingPage.setMainPhoto(updatedPage.getMainPhoto());
//        if (updatedPage.getQrCode() != null) existingPage.setQrCode(updatedPage.getQrCode());
//        if (updatedPage.getCemetery() != null) existingPage.setCemetery(updatedPage.getCemetery());
//
//        existingPage.setUpdatedAt(LocalDateTime.now());
//
//        return memorialPagesRepository.save(existingPage);
//    }
//
//    @Transactional
//    @CacheEvict(value = "memorialPages", allEntries = true)
//    public void deleteMemorialPage(Integer id) {
//        if (!memorialPagesRepository.existsById(id)) {
//            throw new NotFoundException("Memorial page not found with ID: " + id);
//        }
//        memorialPagesRepository.deleteById(id);
//    }
}
