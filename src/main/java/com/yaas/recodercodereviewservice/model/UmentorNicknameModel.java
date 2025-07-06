package com.yaas.recodercodereviewservice.model;

public class UmentorNicknameModel {
    private String mentorNickname;

    public UmentorNicknameModel() {
    }

    public String getMentorNickname() {
        return this.mentorNickname;
    }

    public void setMentorNickname(final String mentorNickname) {
        this.mentorNickname = mentorNickname;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof UmentorNicknameModel)) {
            return false;
        } else {
            UmentorNicknameModel other = (UmentorNicknameModel)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
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

    protected boolean canEqual(final Object other) {
        return other instanceof UmentorNicknameModel;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $mentorNickname = this.getMentorNickname();
        result = result * 59 + ($mentorNickname == null ? 43 : $mentorNickname.hashCode());
        return result;
    }

    public String toString() {
        return "UmentorNicknameModel(mentorNickname=" + this.getMentorNickname() + ")";
    }
}
