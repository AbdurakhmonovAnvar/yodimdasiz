package com.yodimdasiz.yodimdasiz.service;

import com.yodimdasiz.yodimdasiz.model.Prayers;
import com.yodimdasiz.yodimdasiz.repository.PrayersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrayersService {
    private final PrayersRepository prayersRepository;

    public Prayers createPrayer(Prayers prayer) {
        return prayersRepository.save(prayer);
    }

    public List<Prayers> getAllPrayers() {
        return prayersRepository.findAll();
    }

    public Prayers getPrayerById(Integer id) {
        return prayersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prayer not found"));
    }

    @Transactional
    public Prayers updatePrayer(Integer id, Prayers updatedPrayer) {
        Prayers existingPrayer = getPrayerById(id);
        existingPrayer.setMemorial(updatedPrayer.getMemorial());
        existingPrayer.setUser(updatedPrayer.getUser());
        existingPrayer.setPrayerType(updatedPrayer.getPrayerType());
        return prayersRepository.save(existingPrayer);
    }

    public void deletePrayer(Integer id) {
        Prayers prayer = getPrayerById(id);
        prayersRepository.delete(prayer);
    }
}
