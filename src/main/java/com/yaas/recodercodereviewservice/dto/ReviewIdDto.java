package com.yaas.recodercodereviewservice.dto;

public class ReviewIdDto {
    private long reviewId;

    public long getReviewId() {
        return this.reviewId;
    }

    public void setReviewId(final long reviewId) {
        this.reviewId = reviewId;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ReviewIdDto)) {
            return false;
        } else {
            ReviewIdDto other = (ReviewIdDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                return this.getReviewId() == other.getReviewId();
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ReviewIdDto;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $reviewId = this.getReviewId();
        result = result * 59 + (int)($reviewId >>> 32 ^ $reviewId);
        return result;
    }

    public String toString() {
        return "ReviewIdDto(reviewId=" + this.getReviewId() + ")";
    }

    public ReviewIdDto(final long reviewId) {
        this.reviewId = reviewId;
    }

    public ReviewIdDto() {
    }
}
