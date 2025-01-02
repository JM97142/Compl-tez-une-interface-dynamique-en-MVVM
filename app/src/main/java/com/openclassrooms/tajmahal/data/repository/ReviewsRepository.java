package com.openclassrooms.tajmahal.data.repository;

import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.List;

public class ReviewsRepository {

    public List<Review> getReviews() {
        return (List<Review>) new RestaurantFakeApi().getReviews();
    }
}
