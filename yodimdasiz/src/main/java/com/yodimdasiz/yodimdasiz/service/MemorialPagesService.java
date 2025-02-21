package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.exception.NotFoundException;
import com.yodimdasiz.yodimdasiz.model.MemorialPages;
import com.yodimdasiz.yodimdasiz.repository.MemorialPagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemorialPagesService {
    private final MemorialPagesRepository memorialPagesRepository;

    @Cacheable("memorialPages")
    public List<MemorialPages> getAllMemorialPages() {
        return memorialPagesRepository.findAll();
    }

    public MemorialPages getMemorialPageById(Integer id) {
        return memorialPagesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Memorial page not found with ID: " + id));
    }

    public List<MemorialPages> getMemorialPagesByUserId(Integer userId) {
        return memorialPagesRepository.getMemorialPagesByUserId(userId);
    }

    @Transactional
    @CacheEvict(value = "memorialPages", allEntries = true)
    public MemorialPages createMemorialPage(MemorialPages memorialPage) {
        memorialPage.setCreatedAt(LocalDateTime.now());
        return memorialPagesRepository.save(memorialPage);
    }

    @Transactional
    @CacheEvict(value = "memorialPages", allEntries = true)
    public MemorialPages updateMemorialPage(Integer id, MemorialPages updatedPage) {
        MemorialPages existingPage = getMemorialPageById(id);

        if (updatedPage.getFullName() != null) existingPage.setFullName(updatedPage.getFullName());
        if (updatedPage.getBirthDate() != null) existingPage.setBirthDate(updatedPage.getBirthDate());
        if (updatedPage.getDeathDate() != null) existingPage.setDeathDate(updatedPage.getDeathDate());
        if (updatedPage.getBiography() != null) existingPage.setBiography(updatedPage.getBiography());
        if (updatedPage.getEpitaph() != null) existingPage.setEpitaph(updatedPage.getEpitaph());
        if (updatedPage.getMainPhoto() != null) existingPage.setMainPhoto(updatedPage.getMainPhoto());
        if (updatedPage.getQrCode() != null) existingPage.setQrCode(updatedPage.getQrCode());
        if (updatedPage.getCemetery() != null) existingPage.setCemetery(updatedPage.getCemetery());

        existingPage.setUpdatedAt(LocalDateTime.now());

        return memorialPagesRepository.save(existingPage);
    }

    @Transactional
    @CacheEvict(value = "memorialPages", allEntries = true)
    public void deleteMemorialPage(Integer id) {
        if (!memorialPagesRepository.existsById(id)) {
            throw new NotFoundException("Memorial page not found with ID: " + id);
        }
        memorialPagesRepository.deleteById(id);
    }
}
