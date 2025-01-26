package com.airbnb.service;

import com.airbnb.payload.ReviewsDto;

import java.util.List;

public interface ReviewsService {
    ReviewsDto createReview(Long propertyId, Long userId, ReviewsDto dto);
    void deleteReviews(Long id);

    ReviewsDto getReviewsById(Long id);

    ReviewsDto updateReviews(Long id,   ReviewsDto dto);

    List<ReviewsDto> getReviewsByPropertyAndUser(Long propertyId, Long userId);
}
