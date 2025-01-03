package com.openclassrooms.tajmahal.ui.reviews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.openclassrooms.tajmahal.databinding.FragmentReviewsBinding;
import com.openclassrooms.tajmahal.domain.model.Review;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * ReviewFragment is responsible for displaying the list of reviews for the restaurant
 * and allowing users to submit new reviews with a rating.
 * It uses a ViewModel to manage review data and LiveData to observe changes in the data.
 * This fragment provides UI components to display reviews, take user input, and navigate back to the previous screen.
 */
@AndroidEntryPoint
public class ReviewsFragment extends Fragment {

    private FragmentReviewsBinding binding;
    private ReviewViewModel reviewViewModel;
    private float userRating = 0;

    /**
     * Default constructor for ReviewFragment. No arguments required.
     */
    public ReviewsFragment() {
        // Required empty public constructor
    }

    /**
     * Inflates the layout for the fragment using ViewBinding.
     * @param inflater The LayoutInflater object to inflate the layout.
     * @param container The parent view group.
     * @param savedInstanceState The saved state of the fragment (if any).
     * @return The root view of the fragment.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReviewsBinding.inflate(inflater, container, false);
        return binding.getRoot(); // Returns the root view.
    }

    /**
     * Called when the view has been created.
     * Initializes the toolbar, RecyclerView, and observes the review data.
     * Also sets up listeners for the rating bar and review submission button.
     * @param view The root view of the fragment.
     * @param savedInstanceState The saved state of the fragment (if any).
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                }
                else {
                    getActivity().finish();
                }
            }
        });

        reviewViewModel = new ViewModelProvider(requireActivity()).get(ReviewViewModel.class);

        binding.usersReviews.setLayoutManager(new LinearLayoutManager(getContext()));

        reviewViewModel.getReviews().observe(getViewLifecycleOwner(), reviews -> {
            if (reviews != null) {
                ReviewListAdapter adapter = new ReviewListAdapter(reviews);
                binding.usersReviews.setAdapter(adapter);
            }
        });

        reviewRate();

        binding.buttonValidate.setOnClickListener(v -> newReview());
    }

    /**
     *
     * Save a review rate
     */
    public void reviewRate() {
        binding.reviewRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                userRating = rating;
            }
        });

    }

    private void newReview() {
        String activeUser = binding.userName.getText().toString();
        String reviewText = binding.reviewEdit.getText().toString();
        int rating = Math.round(binding.reviewRate.getRating()); // Get the rounded rating from the rating bar

        Review newReview = new Review(activeUser, "@drawable/avatar.png", reviewText, rating);

        if (validateReviewData()){
            reviewViewModel.addReview(newReview);

            binding.reviewEdit.setText("");
            binding.reviewRate.setRating(0);
        }
    }

    private boolean validateReviewData() {
        String reviewText = binding.reviewEdit.getText().toString();
        float rating = binding.reviewRate.getRating();

        if (reviewText.isEmpty()) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (rating == 0) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}