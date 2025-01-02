package com.openclassrooms.tajmahal.ui.reviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.openclassrooms.tajmahal.R;
import com.openclassrooms.tajmahal.domain.model.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * ReviewListAdapter is responsible for binding a list of reviews to a RecyclerView.
 * It displays each review with the username, comment, rating, and avatar image.
 * The adapter uses Glide to load the user's avatar image.
 */
public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.MyViewHolder> {

    private List<Review> reviewList;

    /**
     * Constructor for the ReviewListAdapter that initializes the review list.
     * @param reviews The list of reviews to be displayed.
     */
    public ReviewListAdapter(List<Review> reviews) {
        if (reviews != null) {
            this.reviewList = reviews;
        } else {
            this.reviewList = new ArrayList<>();
        }
    }

    /**
     * Called when the RecyclerView needs a new ViewHolder to be created.
     * Inflates the layout for each individual review item.
     * @param parent The parent view that the new item view will be attached to.
     * @param viewType The view type of the new view (if needed).
     * @return A new ViewHolder instance.
     */
    @NonNull
    @Override
    public ReviewListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the review item layout and return a new ViewHolder
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new MyViewHolder(itemView);
    }

    /**
     * Binds the data from the review list to the ViewHolder for each individual item.
     * This method populates the review details into the respective UI components.
     * @param holder The ViewHolder that holds the views for an individual review item.
     * @param position The position in the list of reviews that should be displayed.
     */
    @Override
    public void onBindViewHolder(@NonNull ReviewListAdapter.MyViewHolder holder, int position) {
        Review review = reviewList.get(position);

        holder.commentTextView.setText(review.getComment());
        holder.userInList.setText(review.getUsername());

        holder.ratingbarSetup.setRating(review.getRate());

        Glide.with(holder.itemView.getContext())
                .load(review.getPicture()) // Load the URL of the avatar image
                .circleCrop() // Crop the image to make it circular
                .into(holder.avatarView); // Set the image into the ImageView
    }

    /**
     * Returns the number of items in the review list.
     * @return The total number of reviews in the list.
     */
    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    /**
     * MyViewHolder is the ViewHolder class that holds the views for each individual review item.
     */
    protected class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userInList;
        public TextView commentTextView;
        public ImageView avatarView;
        public RatingBar ratingbarSetup;

        /**
         * Constructor for MyViewHolder. Initializes all the views in the item layout.
         * @param itemView The root view of the individual review item.
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userInList = itemView.findViewById(R.id.userName);
            commentTextView = itemView.findViewById(R.id.commentTextView);
            avatarView = itemView.findViewById(R.id.userAvatar);
            ratingbarSetup = itemView.findViewById(R.id.ratingBar);
        }
    }
}