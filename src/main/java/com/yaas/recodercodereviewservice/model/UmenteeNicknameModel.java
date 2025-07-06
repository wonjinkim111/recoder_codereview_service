package com.yaas.recodercodereviewservice.model;

public class UmenteeNicknameModel {
    private String menteeNickname;

    public UmenteeNicknameModel() {
    }

    public String getMenteeNickname() {
        return this.menteeNickname;
    }

    public void setMenteeNickname(final String menteeNickname) {
        this.menteeNickname = menteeNickname;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof UmenteeNicknameModel)) {
            return false;
        } else {
            UmenteeNicknameModel other = (UmenteeNicknameModel)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$menteeNickname = this.getMenteeNickname();
                Object other$menteeNickname = other.getMenteeNickname();
                if (this$menteeNickname == null) {
                    if (other$menteeNickname != null) {
                        return false;
                    }
                } else if (!this$menteeNickname.equals(other$menteeNickname)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UmenteeNicknameModel;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $menteeNickname = this.getMenteeNickname();
        result = result * 59 + ($menteeNickname == null ? 43 : $menteeNickname.hashCode());
        return result;
    }

    public String toString() {
        return "UmenteeNicknameModel(menteeNickname=" + this.getMenteeNickname() + ")";
    }
}
