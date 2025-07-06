package com.yaas.recodercodereviewservice.model;

public class CreateReviewFileResponseModel {
    private long reviewId;
    private long roomId;
    private long mentorId;
    private long menteeId;
    private String reviewTitle;
    private String reviewContent;
    private String reviewCodePath;
    private int reviewLanguage;

    public long getReviewId() {
        return this.reviewId;
    }

    public long getRoomId() {
        return this.roomId;
    }

    public long getMentorId() {
        return this.mentorId;
    }

    public long getMenteeId() {
        return this.menteeId;
    }

    public String getReviewTitle() {
        return this.reviewTitle;
    }

    public String getReviewContent() {
        return this.reviewContent;
    }

    public String getReviewCodePath() {
        return this.reviewCodePath;
    }

    public int getReviewLanguage() {
        return this.reviewLanguage;
    }

    public void setReviewId(final long reviewId) {
        this.reviewId = reviewId;
    }

    public void setRoomId(final long roomId) {
        this.roomId = roomId;
    }

    public void setMentorId(final long mentorId) {
        this.mentorId = mentorId;
    }

    public void setMenteeId(final long menteeId) {
        this.menteeId = menteeId;
    }

    public void setReviewTitle(final String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public void setReviewContent(final String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public void setReviewCodePath(final String reviewCodePath) {
        this.reviewCodePath = reviewCodePath;
    }

    public void setReviewLanguage(final int reviewLanguage) {
        this.reviewLanguage = reviewLanguage;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CreateReviewFileResponseModel)) {
            return false;
        } else {
            CreateReviewFileResponseModel other = (CreateReviewFileResponseModel)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getReviewId() != other.getReviewId()) {
                return false;
            } else if (this.getRoomId() != other.getRoomId()) {
                return false;
            } else if (this.getMentorId() != other.getMentorId()) {
                return false;
            } else if (this.getMenteeId() != other.getMenteeId()) {
                return false;
            } else {
                Object this$reviewTitle = this.getReviewTitle();
                Object other$reviewTitle = other.getReviewTitle();
                if (this$reviewTitle == null) {
                    if (other$reviewTitle != null) {
                        return false;
                    }
                } else if (!this$reviewTitle.equals(other$reviewTitle)) {
                    return false;
                }

                label54: {
                    Object this$reviewContent = this.getReviewContent();
                    Object other$reviewContent = other.getReviewContent();
                    if (this$reviewContent == null) {
                        if (other$reviewContent == null) {
                            break label54;
                        }
                    } else if (this$reviewContent.equals(other$reviewContent)) {
                        break label54;
                    }

                    return false;
                }

                label47: {
                    Object this$reviewCodePath = this.getReviewCodePath();
                    Object other$reviewCodePath = other.getReviewCodePath();
                    if (this$reviewCodePath == null) {
                        if (other$reviewCodePath == null) {
                            break label47;
                        }
                    } else if (this$reviewCodePath.equals(other$reviewCodePath)) {
                        break label47;
                    }

                    return false;
                }

                if (this.getReviewLanguage() != other.getReviewLanguage()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreateReviewFileResponseModel;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $reviewId = this.getReviewId();
        result = result * 59 + (int)($reviewId >>> 32 ^ $reviewId);
        long $roomId = this.getRoomId();
        result = result * 59 + (int)($roomId >>> 32 ^ $roomId);
        long $mentorId = this.getMentorId();
        result = result * 59 + (int)($mentorId >>> 32 ^ $mentorId);
        long $menteeId = this.getMenteeId();
        result = result * 59 + (int)($menteeId >>> 32 ^ $menteeId);
        Object $reviewTitle = this.getReviewTitle();
        result = result * 59 + ($reviewTitle == null ? 43 : $reviewTitle.hashCode());
        Object $reviewContent = this.getReviewContent();
        result = result * 59 + ($reviewContent == null ? 43 : $reviewContent.hashCode());
        Object $reviewCodePath = this.getReviewCodePath();
        result = result * 59 + ($reviewCodePath == null ? 43 : $reviewCodePath.hashCode());
        result = result * 59 + this.getReviewLanguage();
        return result;
    }

    public String toString() {
        return "CreateReviewFileResponseModel(reviewId=" + this.getReviewId() + ", roomId=" + this.getRoomId() + ", mentorId=" + this.getMentorId() + ", menteeId=" + this.getMenteeId() + ", reviewTitle=" + this.getReviewTitle() + ", reviewContent=" + this.getReviewContent() + ", reviewCodePath=" + this.getReviewCodePath() + ", reviewLanguage=" + this.getReviewLanguage() + ")";
    }

    public CreateReviewFileResponseModel(final long reviewId, final long roomId, final long mentorId, final long menteeId, final String reviewTitle, final String reviewContent, final String reviewCodePath, final int reviewLanguage) {
        this.reviewId = reviewId;
        this.roomId = roomId;
        this.mentorId = mentorId;
        this.menteeId = menteeId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewCodePath = reviewCodePath;
        this.reviewLanguage = reviewLanguage;
    }

    public CreateReviewFileResponseModel() {
    }
}
