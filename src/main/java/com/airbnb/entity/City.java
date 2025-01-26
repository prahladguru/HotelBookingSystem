package com.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name= "city_name",nullable = false,unique = true)
    private String cityName;

    @OneToMany(mappedBy = "city",orphanRemoval = true)
    private Set<Property> properties = new LinkedHashSet<>();
}
