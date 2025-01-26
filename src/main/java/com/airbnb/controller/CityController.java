package com.airbnb.controller;


import com.airbnb.payload.CityDto;
import com.airbnb.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    private final CityService cityService;


    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/create")
    public ResponseEntity<CityDto> addCity(@RequestBody CityDto cityDto,
                                           @RequestParam("country_id") long countryId){
        CityDto dto = cityService.addCity(cityDto, countryId);
        return new ResponseEntity<>(dto , HttpStatus.CREATED);
    }
}
