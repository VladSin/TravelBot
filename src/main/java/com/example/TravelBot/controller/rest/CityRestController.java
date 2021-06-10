package com.example.TravelBot.controller.rest;

import com.example.TravelBot.dto.request.CityRequestDto;
import com.example.TravelBot.dto.response.CityResponseDto;
import com.example.TravelBot.entity.CityEntity;
import com.example.TravelBot.service.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @PostMapping(value = "save")
    @ApiOperation(value = "Save City", response = CityResponseDto.class, tags = "saveCity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    public ResponseEntity<CityResponseDto> saveCity(@RequestBody CityRequestDto request) {
        CityEntity city = new CityEntity();
        city.setName(request.getName());
        city.setInfo(request.getInfo());

        CityEntity savedCity = cityService.save(city);
        CityResponseDto result = new CityResponseDto(savedCity.getId(), savedCity.getName(), savedCity.getInfo());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get/{id}")
    @ApiOperation(value = "Get City by Id", response = CityResponseDto.class, tags = "getCityById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 204, message = "NO_CONTENT")})
    public ResponseEntity<CityResponseDto> getCityById(@PathVariable(name = "id") Long id) {
        CityEntity city = cityService.findById(id);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        CityResponseDto result = new CityResponseDto(city.getId(), city.getName(), city.getInfo());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get/name/{name}")
    @ApiOperation(value = "Get City by Name", response = CityResponseDto.class, tags = "getCityByName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 204, message = "NO_CONTENT")})
    public ResponseEntity<CityResponseDto> getCityByName(@PathVariable(name = "name") String name) {
        CityEntity city = cityService.findByName(name);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        CityResponseDto result = new CityResponseDto(city.getId(), city.getName(), city.getInfo());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("get/all")
    public ResponseEntity<List<CityEntity>> cities() {
        return new ResponseEntity<>(cityService.getAll(), HttpStatus.OK);
    }

    @PatchMapping(value = "update/name/{id}")
    @ApiOperation(value = "Update City", response = CityResponseDto.class, tags = "updateCityName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 204, message = "NO_CONTENT")})
    public ResponseEntity<CityResponseDto> updateCityName(
            @PathVariable("id") Long id,
            @RequestBody CityRequestDto request) {

        if (cityService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        cityService.updateName(id, request.getName());

        CityEntity updatedCity = cityService.findById(id);
        CityResponseDto result = new CityResponseDto(updatedCity.getId(), updatedCity.getName(), updatedCity.getInfo());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping(value = "update/info/{id}")
    @ApiOperation(value = "Update Info", response = CityResponseDto.class, tags = "updateCityInformation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 204, message = "NO_CONTENT")})
    public ResponseEntity<CityResponseDto> updateCityInformation(
            @PathVariable("id") Long id,
            @RequestBody CityRequestDto request) {
        if (cityService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        cityService.updateInfo(id, request.getInfo());

        CityEntity updatedCity = cityService.findById(id);
        CityResponseDto result = new CityResponseDto(updatedCity.getId(), updatedCity.getName(), updatedCity.getInfo());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "delete/{id}")
    @ApiOperation(value = "Delete City", tags = "deleteCity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK")})
    public void deleteCity(@PathVariable("id") Long id) {
        cityService.delete(id);
    }
}
