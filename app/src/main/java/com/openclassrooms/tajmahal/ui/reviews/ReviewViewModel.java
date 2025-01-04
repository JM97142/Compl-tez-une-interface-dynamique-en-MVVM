package com.openclassrooms.tajmahal.ui.reviews;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.tajmahal.data.repository.ReviewsRepository;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * ReviewViewModel responsible for managing reviews data.
 * It interacts with the repository to obtain reviews and exposes them via LiveData.
 * This ViewModel is used to ensure the UI only interacts with live data.
 */
public class ReviewViewModel extends ViewModel {
    private final ReviewsRepository reviewsRepository;
    private final MutableLiveData<List<Review>> liveDataReviews = new MutableLiveData<>(new ArrayList<>());

    /**
     *
     * Initializes the ViewModel an loading the data.
     */
    public ReviewViewModel() {
        reviewsRepository = new ReviewsRepository();
        reviewsLoaded();
    }

    /**
     *
     * This method allows the UI to observe changes in the list of reviews.
     * @return An instance of LiveData containing the current list of reviews.
     */
    public MutableLiveData<List<Review>> getReviews() {
        return liveDataReviews;
    }

    /**
     *
     * This method is called during the initialization of the ViewModel to load initial data.
     * Loads the reviews from the repository and publishes them via LiveData.
     */
    public void reviewsLoaded() {
        List<Review> reviews = reviewsRepository.getReviews();
        liveDataReviews.postValue(reviews);
    }

    /**
     * Adds a new review to the list and updates the LiveData.
     * A valid review must have a non-empty comment and a rating greater than 0.
     * The review is added to the beginning of the list.
     *
     * @param review to add a new review.
     * @return true if the review is successfully added.
     */
    public void addNewReview(Review review) {
        if (review.getComment().isEmpty() || review.getRate() <= 0) {
            return;
        }

        List<Review> currentReviews = new ArrayList<>(liveDataReviews.getValue());
        currentReviews.add(0, review); // Add the new review at the start of the list

        liveDataReviews.setValue(currentReviews);
    }
}