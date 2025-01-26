package com.airbnb.payload;

import com.airbnb.entity.Country;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CityDto {

    private Long id;
    private String cityName;
    private Country country;



}
