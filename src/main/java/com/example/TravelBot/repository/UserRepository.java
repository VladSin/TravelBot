package com.example.TravelBot.repository;

import com.example.TravelBot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity set username = :username where id = :id")
    void updateUsername(@Param("id") Long id, @Param("username") String username);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity set password = :password where id = :id")
    void updatePassword(@Param("id") Long id, @Param("password") String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserEntity set email = :email where id = :id")
    void updateEmail(@Param("id") Long id, @Param("email") String email);
}
