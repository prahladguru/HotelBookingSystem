package com.airbnb.repository;

import com.airbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

//    @Query("SELECT p FROM Property p JOIN p.city c WHERE c.cityName = :cityName")
//                List<Property> searchProperty(
//                       @RequestParam("city") String cityName
//                );

}