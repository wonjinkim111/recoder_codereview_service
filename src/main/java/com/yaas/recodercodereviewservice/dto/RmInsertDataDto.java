package com.yaas.recodercodereviewservice.dto;

public class RmInsertDataDto {
    private long roomId;
    private long menteeId;

    public long getRoomId() {
        return this.roomId;
    }

    public long getMenteeId() {
        return this.menteeId;
    }

    public void setRoomId(final long roomId) {
        this.roomId = roomId;
    }

    public void setMenteeId(final long menteeId) {
        this.menteeId = menteeId;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RmInsertDataDto)) {
            return false;
        } else {
            RmInsertDataDto other = (RmInsertDataDto)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getRoomId() != other.getRoomId()) {
                return false;
            } else {
                return this.getMenteeId() == other.getMenteeId();
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RmInsertDataDto;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $roomId = this.getRoomId();
        result = result * 59 + (int)($roomId >>> 32 ^ $roomId);
        long $menteeId = this.getMenteeId();
        result = result * 59 + (int)($menteeId >>> 32 ^ $menteeId);
        return result;
    }

    public String toString() {
        return "RmInsertDataDto(roomId=" + this.getRoomId() + ", menteeId=" + this.getMenteeId() + ")";
    }

    public RmInsertDataDto(final long roomId, final long menteeId) {
        this.roomId = roomId;
        this.menteeId = menteeId;
    }

    public RmInsertDataDto() {
    }
}
