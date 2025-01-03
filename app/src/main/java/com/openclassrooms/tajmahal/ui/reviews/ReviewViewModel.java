package com.openclassrooms.tajmahal.ui.reviews;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.tajmahal.data.repository.ReviewsRepository;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewViewModel extends ViewModel {
    private final ReviewsRepository reviewsRepository;
    private final MutableLiveData<List<Review>> liveDataReviews = new MutableLiveData<>(new ArrayList<>());

    /**
     * Initializes the ViewModel.
     */
    public ReviewViewModel() {
        reviewsRepository = new ReviewsRepository();
        reviewsLoaded();
    }

    public MutableLiveData<List<Review>> getReviews() {
        return liveDataReviews;
    }

    public void reviewsLoaded() {
        List<Review> reviews = reviewsRepository.getReviews();
        liveDataReviews.postValue(reviews);
    }

    public void addReview(Review review) {
        if (review.getComment().isEmpty() || review.getRate() <= 0) {
            return;
        }

        List<Review> currentReviews = new ArrayList<>(liveDataReviews.getValue());
        currentReviews.add(0, review); // Add the new review at the start of the list

        liveDataReviews.setValue(currentReviews);
    }
}