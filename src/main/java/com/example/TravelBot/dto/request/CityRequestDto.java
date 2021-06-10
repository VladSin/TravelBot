package com.example.TravelBot.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityRequestDto {

    @ApiModelProperty(notes = "Name of the City", name = "name", required = true, value = "test name")
    private String name;
    @ApiModelProperty(notes = "Info of the City", name = "info", required = true, value = "test info")
    private String info;

    @Override
    public String toString() {
        return "CityRequestDto{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
