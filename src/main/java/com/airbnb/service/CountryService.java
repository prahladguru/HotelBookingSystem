package com.airbnb.service;

import com.airbnb.payload.CountryDto;

import java.util.List;

public interface CountryService {
    CountryDto addCountry(CountryDto countryDto);

    void deleteCity(long id);

    CountryDto updateCountry(long id,CountryDto countryDto);

    List<CountryDto> getCountry();
}
