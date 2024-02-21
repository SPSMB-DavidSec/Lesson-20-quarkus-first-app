package org.acme.dto;

public class RatingDTO {
    int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RatingDTO{" +
                "rating=" + rating +
                '}';
    }
}
