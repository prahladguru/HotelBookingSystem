package com.airbnb.controller;

import com.airbnb.entity.Country;
import com.airbnb.payload.PropertyDto;
import com.airbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private final PropertyService propertyService;


    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
     // http://localhost:8080/api/v1/property/create?country_id=&city_id=

    @PostMapping("/create")
    public ResponseEntity<PropertyDto> addProperty(
            @RequestParam("country_id") Long countryId,
            @RequestParam("city_id") Long cityId,
            @RequestBody PropertyDto dto) {

        PropertyDto dto1 = propertyService.addProperty(countryId, cityId, dto);
        return new ResponseEntity<>(dto1, HttpStatus.CREATED);
    }


    @DeleteMapping("delete")
    public ResponseEntity<String> deleteProperty(@RequestParam long id){
        propertyService.deleteProperty(id);
        return new ResponseEntity<>("Property Deleted",HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PropertyDto> updateProperty(@RequestParam("id") Long id ,
                                                      @RequestParam(value = "country_id", required = false) Long countryId,
                                                      @RequestParam(value = "city_id") Long cityId ,
                                                      @RequestBody PropertyDto dto){
        PropertyDto updated = propertyService.updateProperty(id, countryId, cityId, dto);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

//    public ResponseEntity<List<PropertyDto>> searchProperty(
//            @RequestParam(required = false) String cityName,
//            @RequestParam(required = false) String countryName){
//       return null;
//    }
}
