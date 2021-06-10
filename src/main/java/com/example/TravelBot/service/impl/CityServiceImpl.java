package com.example.TravelBot.service.impl;

import com.example.TravelBot.entity.CityEntity;
import com.example.TravelBot.repository.CityRepository;
import com.example.TravelBot.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityEntity save(CityEntity city) {
        log.info("IN saved - city: {} successfully saved", city);
        return cityRepository.save(city);
    }

    @Override
    public CityEntity findById(Long id) {
        CityEntity city = cityRepository.findById(id).orElse(null);
        if (city == null) {
            log.warn("IN findById - no city found by id: {}", id);
            return null;
        }
        log.info("IN findById - city: {} found by id: {}", city, city.getId());
        return city;
    }

    @Override
    public CityEntity findByName(String name) {
        log.warn("IN findByName - city found: {}", name);
        return cityRepository.findByName(name);
    }

    @Override
    public List<CityEntity> getAll() {
        List<CityEntity> cityEntities = cityRepository.findAll();
        log.info("IN getAll - {} cityEntities found", cityEntities.size());
        return cityEntities;
    }

    @Override
    public void updateName(Long id, String name) {
        cityRepository.updateName(id, name);
        log.info("IN updateName - city with id: {} successfully updated", id);
    }

    @Override
    public void updateInfo(Long id, String info) {
        cityRepository.updateInfo(id, info);
        log.info("IN updateInfo - information for the city with id: {} successfully updated", id);
    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
        log.info("IN delete - city with id: {} successfully deleted", id);
    }
}
