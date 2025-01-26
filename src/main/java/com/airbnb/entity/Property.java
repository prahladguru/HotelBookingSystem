package com.airbnb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "property_name", nullable = false)
    private String propertyName;

    @Column(name = "number_of_guests", nullable = false)
    private Integer numberOfGuests;

    @Column(name = "number_of_beds", nullable = false)
    private Integer numberOfBeds;

    @Column(name = "number_of_bathrooms", nullable = false)
    private Integer numberOfBathrooms;

    @Column(name = "number_of_bedrooms")
    private Integer numberOfBedrooms;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonBackReference("Country")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference("City")
    private City city;

    @OneToMany(mappedBy = "property", orphanRemoval = true)
    @JsonManagedReference("Property")
    private Set<Reviews> reviews = new LinkedHashSet<>();


}