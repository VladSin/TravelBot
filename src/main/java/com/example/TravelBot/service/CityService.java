package com.example.TravelBot.service;

import com.example.TravelBot.entity.CityEntity;

import java.util.List;

public interface CityService {

    CityEntity save(CityEntity city);

    CityEntity findById(Long id);

    CityEntity findByName(String name);

    List<CityEntity> getAll();

    void updateName(Long id, String name);

    void updateInfo(Long id, String info);

    void delete(Long id);
}
