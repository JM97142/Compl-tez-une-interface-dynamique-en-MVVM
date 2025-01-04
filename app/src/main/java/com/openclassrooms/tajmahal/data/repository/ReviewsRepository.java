package com.openclassrooms.tajmahal.data.repository;

import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.List;

/**
 * This method will make a network call using the provided {@link RestaurantFakeApi} instance.
 *
 * @return List holding the review.
 */
public class ReviewsRepository {

    public List<Review> getReviews() {
        return (List<Review>) new RestaurantFakeApi().getReviews();
    }
}
