package com.airbnb.repository;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
//    @Query("Select r from Review r where r.property=:property and r.appUser=:user")
//    Reviews findByUserAndProperty(
//            @Param("user") AppUser user,
//            @Param("property") Property property
//            );
}