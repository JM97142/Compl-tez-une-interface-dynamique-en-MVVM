package com.openclassrooms.tajmahal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.openclassrooms.tajmahal.domain.model.Review;
import com.openclassrooms.tajmahal.ui.reviews.ReviewViewModel;

import java.util.List;

/**
 * class represents a unit test for the AddNewReview functionality.
 *
 */
public class UnitTest {

        @Rule
        public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

        private ReviewViewModel reviewViewModel;

        @Mock
        private Observer<List<Review>> observer;

        @Before
        public void setUp() {

            reviewViewModel = new ReviewViewModel();
            observer = mock(Observer.class);
            reviewViewModel.getReviews().observeForever(observer);
        }

        /**
         * Local unit test for adding a new review to the list.
         *
         */
        @Test
        public void addReviewToList() {
            Review review = new Review("Manon Garcia", "https://xsgames.co/randomusers/assets/avatars/female/0.jpg", "Test review", 5);

            boolean result = reviewViewModel.addNewReview(review);

            List<Review> listReviews = reviewViewModel.getReviews().getValue();
            listReviews.add(review);

            assertTrue(result);
            assertEquals(listReviews, reviewViewModel.getReviews().getValue());
        }
}