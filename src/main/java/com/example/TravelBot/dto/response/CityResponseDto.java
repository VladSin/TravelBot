package com.example.TravelBot.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityResponseDto {

    private Long id;
    private String name;
    private String info;

    public CityResponseDto(Long id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    @Override
    public String toString() {
        return "CityResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
