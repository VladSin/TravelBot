package com.example.TravelBot.controller.rest;

import com.example.TravelBot.dto.request.CityRequestDto;
import com.example.TravelBot.dto.response.CityResponseDto;
import com.example.TravelBot.entity.CityEntity;
import com.example.TravelBot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/city/")
public class CityRestController {

    private final CityService cityService;

    @Autowired
    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @PutMapping(value = "save")
    public ResponseEntity<CityResponseDto> saveCity(@RequestBody CityRequestDto request) {
        CityEntity city = new CityEntity();
        city.setName(request.getName());
        city.setInfo(request.getInfo());

        CityEntity savedCity = cityService.save(city);
        CityResponseDto result = new CityResponseDto(savedCity.getId(), savedCity.getName(), savedCity.getInfo());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get/{id}")
    public ResponseEntity<CityResponseDto> getCityById(@PathVariable(name = "id") Long id) {
        CityEntity city = cityService.findById(id);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        CityResponseDto result = new CityResponseDto(city.getId(), city.getName(), city.getInfo());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get/name/{name}")
    public ResponseEntity<CityResponseDto> getCityByName(@PathVariable(name = "name") String name) {
        CityEntity city = cityService.findByName(name);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        CityResponseDto result = new CityResponseDto(city.getId(), city.getName(), city.getInfo());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CityEntity>> cities() {
        return new ResponseEntity<>(cityService.getAll(), HttpStatus.OK);
    }

    @PatchMapping(value = "/update/name/{id}")
    public ResponseEntity<CityResponseDto> updateCityName(
            @PathVariable("id") Long id,
            @RequestBody String name) {

        if (cityService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        cityService.updateName(id, name);

        CityEntity updatedCity = cityService.findById(id);
        CityResponseDto result = new CityResponseDto(updatedCity.getId(), updatedCity.getName(), updatedCity.getInfo());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(value = "/update/info/{id}")
    public ResponseEntity<CityResponseDto> updateCityInformation(
            @PathVariable("id") Long id,
            @RequestBody String info) {

        if (cityService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        cityService.updateInfo(id, info);

        CityEntity updatedCity = cityService.findById(id);
        CityResponseDto result = new CityResponseDto(updatedCity.getId(), updatedCity.getName(), updatedCity.getInfo());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void deleteCity(@PathVariable("id") Long id) {
        cityService.delete(id);
    }
}
