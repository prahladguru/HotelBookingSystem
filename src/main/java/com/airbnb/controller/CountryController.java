package com.airbnb.controller;

import com.airbnb.entity.Country;
import com.airbnb.payload.CountryDto;
import com.airbnb.repository.CountryRepository;
import com.airbnb.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/country")
public class CountryController {
    private final CountryService countryService;


    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @PostMapping("/create")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto countryDto){
        CountryDto dto = countryService.addCountry(countryDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCountry(@RequestParam long id){
        countryService.deleteCity(id);
        return new ResponseEntity<>("Country Deleted",HttpStatus.OK);

    }
}
