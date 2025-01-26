package com.airbnb.service.Impl;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.payload.CityDto;
import com.airbnb.repository.CityRepository;
import com.airbnb.repository.CountryRepository;
import com.airbnb.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public CityDto addCity(CityDto cityDto, long countryId) {
        // Convert DTO to Entity
        City city = mapToEntity(cityDto);

        // Fetch the country entity by countryId
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found with id: " + countryId));

        // Set the country to the city
        city.setCountry(country);

        // Save the city entity
        City savedCity = cityRepository.save(city);

        // Convert Entity back to DTO
        return mapToDto(savedCity);
    }

    private CityDto mapToDto(City save) {
        CityDto dto = new CityDto();
        dto.setId(save.getId());
        dto.setCityName(save.getCityName());
        dto.setCountry(save.getCountry());
        return dto;
    }

    private City mapToEntity(CityDto cityDto) {
        City city = new City();
        city.setId(cityDto.getId());
        city.setCityName(cityDto.getCityName()); // Correctly set the cityName here
        return city;
    }
}
