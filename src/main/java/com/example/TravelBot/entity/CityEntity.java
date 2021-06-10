package com.example.TravelBot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "info")
    private String info;

    public CityEntity() {
    }

    public CityEntity(Long id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }
}
