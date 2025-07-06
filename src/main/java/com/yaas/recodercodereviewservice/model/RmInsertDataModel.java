package com.yaas.recodercodereviewservice.model;

public class RmInsertDataModel {
    private long rmInsertResult;

    public RmInsertDataModel() {
    }

    public long getRmInsertResult() {
        return this.rmInsertResult;
    }

    public void setRmInsertResult(final long rmInsertResult) {
        this.rmInsertResult = rmInsertResult;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RmInsertDataModel)) {
            return false;
        } else {
            RmInsertDataModel other = (RmInsertDataModel)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                return this.getRmInsertResult() == other.getRmInsertResult();
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RmInsertDataModel;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $rmInsertResult = this.getRmInsertResult();
        result = result * 59 + (int)($rmInsertResult >>> 32 ^ $rmInsertResult);
        return result;
    }

    public String toString() {
        return "RmInsertDataModel(rmInsertResult=" + this.getRmInsertResult() + ")";
    }
}
