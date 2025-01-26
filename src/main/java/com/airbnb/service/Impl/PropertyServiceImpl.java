package com.airbnb.service.Impl;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.entity.Property;
import com.airbnb.exception.UserExists;
import com.airbnb.payload.CityDto;
import com.airbnb.payload.PropertyDto;
import com.airbnb.repository.CityRepository;
import com.airbnb.repository.CountryRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.service.PropertyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
    private PropertyRepository propertyRepository;
    private CountryRepository countryRepository;
    private CityRepository cityRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, CountryRepository countryRepository, CityRepository cityRepository) {
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public PropertyDto addProperty(long countryId, long cityId, PropertyDto dto) {
        Country country = countryRepository.findById(countryId).get();
        City city = cityRepository.findById(cityId).get();
        dto.setCountry(country);
        dto.setCity(city);
        Property property = mapToEntity(dto);
        Property save = propertyRepository.save(property);
        PropertyDto dto1 = mapToDto(save);
        return dto;
    }

    @Override
    public void deleteProperty(long id) {
        propertyRepository.deleteById(id);
    }


    @Override
    public PropertyDto updateProperty(Long id, Long countryId, Long cityId, PropertyDto dto) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new UserExists("Property not found with id: " + id));

        if (countryId != null) {
            Country country = countryRepository.findById(countryId)
                    .orElseThrow(() -> new UserExists("Country not found with id: " + countryId));
            property.setCountry(country);
        }

        if (cityId != null) {
            City city = cityRepository.findById(cityId)
                    .orElseThrow(() -> new UserExists("City not found with id: " + cityId));
            property.setCity(city);
        }
        property.setPropertyName(dto.getPropertyName());
        property.setNumberOfGuests(dto.getNumberOfGuests());
        property.setNumberOfBedrooms(dto.getNumberOfBedrooms());
        property.setNumberOfBeds(dto.getNumberOfBeds());
        property.setNumberOfBathrooms(dto.getNumberOfBathrooms());
        Property updatedProperty = propertyRepository.save(property);
        return mapToDto(updatedProperty);
    }



    private PropertyDto mapToDto(Property save) {
        PropertyDto dto = new PropertyDto();
        dto.setId(save.getId());
        dto.setCity(save.getCity());
        dto.setCountry(save.getCountry());
        dto.setPropertyName(save.getPropertyName());
        dto.setNumberOfBeds(save.getNumberOfBeds());
        dto.setNumberOfBathrooms(save.getNumberOfBathrooms());
        dto.setNumberOfGuests(save.getNumberOfGuests());
        dto.setNumberOfBedrooms(save.getNumberOfBedrooms());
        return dto;
    }


    private Property mapToEntity(PropertyDto dto) {
        Property property = new Property();
        property.setId(dto.getId());
        property.setCity(dto.getCity());
        property.setCountry(dto.getCountry());
        property.setPropertyName(dto.getPropertyName());
        property.setNumberOfBedrooms(dto.getNumberOfBedrooms());
        property.setNumberOfGuests(dto.getNumberOfGuests());
        property.setNumberOfBeds(dto.getNumberOfBeds());
        property.setNumberOfBathrooms(dto.getNumberOfBathrooms());
        return property;
    }


}
   