package com.airbnb.payload;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewsDto {

    private Long id;
    private Integer rating;
    private String description;
    private Property property;
    private AppUser appUser;

}
