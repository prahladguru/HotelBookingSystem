package com.airbnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false,unique = true)
    private String countryName;


    @OneToMany(mappedBy = "country", orphanRemoval = true)
    private Set<City> cities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "country", orphanRemoval = true)
    private Set<Property> properties = new LinkedHashSet<>();

}