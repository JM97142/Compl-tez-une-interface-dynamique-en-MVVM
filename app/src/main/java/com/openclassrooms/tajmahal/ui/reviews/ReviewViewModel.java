package com.openclassrooms.tajmahal.ui.reviews;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;

import com.openclassrooms.tajmahal.data.repository.ReviewsRepository;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewViewModel extends ViewModel {
    private final ReviewsRepository reviewsRepository;
    private final MutableLiveData<List<Review>> liveDataReviews = new MutableLiveData<>(new ArrayList<>());

    /**
     * Initializes the ViewModel by creating a repository and loading the reviews.
     */
    public ReviewViewModel() {
        reviewsRepository = new ReviewsRepository();
    }

    public MutableLiveData<List<Review>> getReviews() {
        return liveDataReviews;
    }

    public void reviewsLoaded() {
        List<Review> reviews = reviewsRepository.getReviews();
        liveDataReviews.postValue(reviews);
    }
}