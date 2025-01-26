package com.airbnb.service.Impl;


import com.airbnb.entity.Reviews;
import com.airbnb.payload.ReviewsDto;
import com.airbnb.repository.AppUserRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewsRepository;
import com.airbnb.service.ReviewsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final PropertyRepository propertyRepository;
    private final AppUserRepository appUserRepository;

    public ReviewsServiceImpl(ReviewsRepository reviewsRepository, PropertyRepository propertyRepository, AppUserRepository appUserRepository) {
        this.reviewsRepository = reviewsRepository;
        this.propertyRepository = propertyRepository;
        this.appUserRepository = appUserRepository;
    }


    @Override
    public ReviewsDto createReview(Long propertyId, Long userId, ReviewsDto dto) {
        Reviews reviews = new Reviews();
        reviews.setDescription(dto.getDescription());
        reviews.setProperty(dto.getProperty());
        reviews.setRating(dto.getRating());
        reviews.setProperty(propertyRepository.findById(propertyId).get());
        Reviews saved = reviewsRepository.save(reviews);
        return mapToDto(saved);
    }

    @Override
    public void deleteReviews(Long id) {
        reviewsRepository.deleteById(id);
    }

    @Override
    public ReviewsDto getReviewsById(Long id) {
        Reviews reviews = reviewsRepository.findById(id).get();
        return mapToDto(reviews);
    }

    @Override
    public ReviewsDto updateReviews(Long id, ReviewsDto dto) {
        Reviews reviews = reviewsRepository.findById(id).get();
        reviews.setRating(dto.getRating());
        Reviews savedReview = reviewsRepository.save(reviews);
        return mapToDto(savedReview);
    }



    @Override
    public List<ReviewsDto> getReviewsByPropertyAndUser(Long propertyId, Long userId) {
        return List.of();
    }

    private ReviewsDto mapToDto(Reviews reviews){
        ReviewsDto dto = new ReviewsDto();
        dto.setId(reviews.getId());
        dto.setDescription(reviews.getDescription());
        dto.setRating(reviews.getRating());
        dto.setProperty(reviews.getProperty());
        return  dto;
    }
}
