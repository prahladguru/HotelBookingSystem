package com.airbnb.payload;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDto {
    private Long id;
    private String propertyName;
    private Integer numberOfGuests;
    private Integer numberOfBeds;
    private Integer numberOfBathrooms;
    private Integer numberOfBedrooms;
    private Country country;
    private City city;



}


