package com.example.TravelBot.repository;

import com.example.TravelBot.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    CityEntity findByName(String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update CityEntity set name = :name where id = :id")
    void updateName(@Param("id") Long id, @Param("name") String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update CityEntity set info = :info where id = :id")
    void updateInfo(@Param("id") Long id, @Param("info") String info);
}
