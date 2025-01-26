package com.airbnb.service;

import com.airbnb.payload.PropertyDto;

import java.util.List;

public interface PropertyService {
    PropertyDto addProperty(long countryId, long cityId, PropertyDto dto);

    void deleteProperty(long id);

    PropertyDto updateProperty(Long id ,Long countryId, Long cityId ,PropertyDto dto);

//    List<PropertyDto> searchProperties(String cityName,String countryName);
}
