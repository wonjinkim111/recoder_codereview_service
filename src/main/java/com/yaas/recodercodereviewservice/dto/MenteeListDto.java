package com.yaas.recodercodereviewservice.dto;

public class MenteeListDto {
    private int reviewCount;
    private int reviewLanguage;

    public int getReviewCount() {
        return this.reviewCount;
    }

    public int getReviewLanguage() {
        return this.reviewLanguage;
    }

    public void setReviewCount(final int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setReviewLanguage(final int reviewLanguage) {
        this.reviewLanguage = reviewLanguage;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MenteeListDto)) {
            return false;
        } else {
            MenteeListDto other = (MenteeListDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getReviewCount() != other.getReviewCount()) {
                return false;
            } else {
                return this.getReviewLanguage() == other.getReviewLanguage();
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MenteeListDto;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getReviewCount();
        result = result * 59 + this.getReviewLanguage();
        return result;
    }

    public String toString() {
        return "MenteeListDto(reviewCount=" + this.getReviewCount() + ", reviewLanguage=" + this.getReviewLanguage() + ")";
    }

    public MenteeListDto(final int reviewCount, final int reviewLanguage) {
        this.reviewCount = reviewCount;
        this.reviewLanguage = reviewLanguage;
    }

    public MenteeListDto() {
    }
}
