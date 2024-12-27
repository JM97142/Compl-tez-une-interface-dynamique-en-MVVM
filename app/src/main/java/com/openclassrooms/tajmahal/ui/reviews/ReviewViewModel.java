package com.openclassrooms.tajmahal.ui.reviews;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.openclassrooms.tajmahal.data.repository.RestaurantRepository;
import com.openclassrooms.tajmahal.data.service.RestaurantFakeApi;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.List;
import java.util.ArrayList;

public class ReviewViewModel extends ViewModel {
    private final RestaurantRepository restaurantRepository; // Repository to fetch review data
    private final MutableLiveData<List<Review>> reviewsLiveData = new MutableLiveData<>(new ArrayList<>()); // LiveData holding the list of reviews

    /**
     * Initializes the ViewModel by creating a repository and loading the reviews.
     */
    public ReviewViewModel(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository; // Initialize the ReviewRepository
        loadReviews(); // Load the reviews from the repository when the ViewModel is created
    }

    /**
     * Retrieves the reviews exposed via LiveData.
     * This method allows the UI to observe changes in the list of reviews.
     *
     * @return An instance of LiveData containing the current list of reviews.
     */
    public LiveData<List<Review>> getReviews() {
        return reviewsLiveData; // Return LiveData to observe the reviews
    }

    /**
     * Loads the reviews from the repository and publishes them via LiveData.
     * This method is called during the initialization of the ViewModel to load initial data.
     */
    private void loadReviews() {
        List<Review> reviews = (List<Review>) restaurantRepository.getReviews(); // Retrieve the list of reviews from the repository
        reviewsLiveData.postValue(reviews); // Update the LiveData with the list of reviews
    }
}