package com.yaas.recodercodereviewservice.dto;

public class CompileDto {
    private String reviewCodePath;

    public String getReviewCodePath() {
        return this.reviewCodePath;
    }

    public void setReviewCodePath(final String reviewCodePath) {
        this.reviewCodePath = reviewCodePath;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CompileDto)) {
            return false;
        } else {
            CompileDto other = (CompileDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$reviewCodePath = this.getReviewCodePath();
                Object other$reviewCodePath = other.getReviewCodePath();
                if (this$reviewCodePath == null) {
                    if (other$reviewCodePath != null) {
                        return false;
                    }
                } else if (!this$reviewCodePath.equals(other$reviewCodePath)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CompileDto;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $reviewCodePath = this.getReviewCodePath();
        result = result * 59 + ($reviewCodePath == null ? 43 : $reviewCodePath.hashCode());
        return result;
    }

    public String toString() {
        return "CompileDto(reviewCodePath=" + this.getReviewCodePath() + ")";
    }

    public CompileDto(final String reviewCodePath) {
        this.reviewCodePath = reviewCodePath;
    }

    public CompileDto() {
    }
}
