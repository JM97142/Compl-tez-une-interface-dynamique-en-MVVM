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
 * Class represents unit tests for the adding a review functionality.
 *
 */
public class UnitTest {

        @Rule
        public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

        private ReviewViewModel reviewViewModel;

        @Mock
        private Observer<List<Review>> observer;

        /**
         * This method is called before each test method
         * for initialize a new ReviewRepository with the mock API.
         */
        @Before
        public void setUp() {

            reviewViewModel = new ReviewViewModel();
            observer = mock(Observer.class);
            reviewViewModel.getReviews().observeForever(observer);
        }

        /**
         * Local unit test for adding a new review to the list.
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

        /**
         * Local unit test for adding a new review to the list without comment.
         */
        @Test
        public void addReviewWithoutComment() {
            Review reviewWithoutComment = new Review("Manon Garcia", "https://xsgames.co/randomusers/assets/avatars/female/0.jpg", "", 5);

            int listSize = reviewViewModel.getReviews().getValue().size();

            boolean result = reviewViewModel.addNewReview(reviewWithoutComment);

            assertFalse(result);
            assertEquals(listSize, reviewViewModel.getReviews().getValue().size());
        }

        /**
         * Local unit test for adding a new review to the list without rate.
         */
        @Test
        public void addReviewWithoutRate() {
            Review reviewWithoutRate = new Review("Manon Garcia", "https://xsgames.co/randomusers/assets/avatars/female/0.jpg", "Test comment", 0);

            int listSize = reviewViewModel.getReviews().getValue().size();

            boolean result = reviewViewModel.addNewReview(reviewWithoutRate);

            assertFalse(result);
            assertEquals(listSize, reviewViewModel.getReviews().getValue().size());
        }
}