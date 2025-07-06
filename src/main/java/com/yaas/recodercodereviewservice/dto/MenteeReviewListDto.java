package com.yaas.recodercodereviewservice.dto;

import java.util.Date;

public class MenteeReviewListDto {
    private long reviewId;
    private long roomId;
    private long mentorId;
    private long menteeId;
    private String reviewTitle;
    private Date reviewRegDate;
    private int reviewLanguage;
    private String reviewCodePath;
    private String mentorNickname;

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

    public Date getReviewRegDate() {
        return this.reviewRegDate;
    }

    public int getReviewLanguage() {
        return this.reviewLanguage;
    }

    public String getReviewCodePath() {
        return this.reviewCodePath;
    }

    public String getMentorNickname() {
        return this.mentorNickname;
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

    public void setReviewRegDate(final Date reviewRegDate) {
        this.reviewRegDate = reviewRegDate;
    }

    public void setReviewLanguage(final int reviewLanguage) {
        this.reviewLanguage = reviewLanguage;
    }

    public void setReviewCodePath(final String reviewCodePath) {
        this.reviewCodePath = reviewCodePath;
    }

    public void setMentorNickname(final String mentorNickname) {
        this.mentorNickname = mentorNickname;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MenteeReviewListDto)) {
            return false;
        } else {
            MenteeReviewListDto other = (MenteeReviewListDto)o;
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
                label73: {
                    Object this$reviewTitle = this.getReviewTitle();
                    Object other$reviewTitle = other.getReviewTitle();
                    if (this$reviewTitle == null) {
                        if (other$reviewTitle == null) {
                            break label73;
                        }
                    } else if (this$reviewTitle.equals(other$reviewTitle)) {
                        break label73;
                    }

                    return false;
                }

                Object this$reviewRegDate = this.getReviewRegDate();
                Object other$reviewRegDate = other.getReviewRegDate();
                if (this$reviewRegDate == null) {
                    if (other$reviewRegDate != null) {
                        return false;
                    }
                } else if (!this$reviewRegDate.equals(other$reviewRegDate)) {
                    return false;
                }

                if (this.getReviewLanguage() != other.getReviewLanguage()) {
                    return false;
                } else {
                    label58: {
                        Object this$reviewCodePath = this.getReviewCodePath();
                        Object other$reviewCodePath = other.getReviewCodePath();
                        if (this$reviewCodePath == null) {
                            if (other$reviewCodePath == null) {
                                break label58;
                            }
                        } else if (this$reviewCodePath.equals(other$reviewCodePath)) {
                            break label58;
                        }

                        return false;
                    }

                    Object this$mentorNickname = this.getMentorNickname();
                    Object other$mentorNickname = other.getMentorNickname();
                    if (this$mentorNickname == null) {
                        if (other$mentorNickname != null) {
                            return false;
                        }
                    } else if (!this$mentorNickname.equals(other$mentorNickname)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MenteeReviewListDto;
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
        Object $reviewRegDate = this.getReviewRegDate();
        result = result * 59 + ($reviewRegDate == null ? 43 : $reviewRegDate.hashCode());
        result = result * 59 + this.getReviewLanguage();
        Object $reviewCodePath = this.getReviewCodePath();
        result = result * 59 + ($reviewCodePath == null ? 43 : $reviewCodePath.hashCode());
        Object $mentorNickname = this.getMentorNickname();
        result = result * 59 + ($mentorNickname == null ? 43 : $mentorNickname.hashCode());
        return result;
    }

    public String toString() {
        return "MenteeReviewListDto(reviewId=" + this.getReviewId() + ", roomId=" + this.getRoomId() + ", mentorId=" + this.getMentorId() + ", menteeId=" + this.getMenteeId() + ", reviewTitle=" + this.getReviewTitle() + ", reviewRegDate=" + this.getReviewRegDate() + ", reviewLanguage=" + this.getReviewLanguage() + ", reviewCodePath=" + this.getReviewCodePath() + ", mentorNickname=" + this.getMentorNickname() + ")";
    }

    public MenteeReviewListDto(final long reviewId, final long roomId, final long mentorId, final long menteeId, final String reviewTitle, final Date reviewRegDate, final int reviewLanguage, final String reviewCodePath, final String mentorNickname) {
        this.reviewId = reviewId;
        this.roomId = roomId;
        this.mentorId = mentorId;
        this.menteeId = menteeId;
        this.reviewTitle = reviewTitle;
        this.reviewRegDate = reviewRegDate;
        this.reviewLanguage = reviewLanguage;
        this.reviewCodePath = reviewCodePath;
        this.mentorNickname = mentorNickname;
    }

    public MenteeReviewListDto() {
    }
}
