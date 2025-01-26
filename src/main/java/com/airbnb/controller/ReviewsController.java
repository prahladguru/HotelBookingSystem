package com.airbnb.controller;

import com.airbnb.payload.ReviewsDto;
import com.airbnb.service.ReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewsController {

    private ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;

    }
    @PostMapping("/create")
    public ResponseEntity<ReviewsDto> createReviews(
                                        @RequestParam("property_id")Long propertyId,
                                        @RequestParam("user_id") Long userId,
                                        @RequestBody ReviewsDto dto){
        ReviewsDto created = reviewsService.createReview(propertyId, userId, dto);
        {
                return new ResponseEntity<>(created, HttpStatus.CREATED);
        }

        }



}
