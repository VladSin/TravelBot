package com.example.TravelBot.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityResponseDto {

    @ApiModelProperty(notes = "Id of the City", name = "id", required = true, value = "test id")
    private Long id;
    @ApiModelProperty(notes = "Name of the City", name = "name", required = true, value = "test name")
    private String name;
    @ApiModelProperty(notes = "Info of the City", name = "info", required = true, value = "test info")
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
