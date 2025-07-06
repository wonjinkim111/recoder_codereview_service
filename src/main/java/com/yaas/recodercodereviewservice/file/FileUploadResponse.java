package com.yaas.recodercodereviewservice.file;

public class FileUploadResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long fileSize;

    public String getFileName() {
        return this.fileName;
    }

    public String getFileDownloadUri() {
        return this.fileDownloadUri;
    }

    public String getFileType() {
        return this.fileType;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public void setFileDownloadUri(final String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public void setFileType(final String fileType) {
        this.fileType = fileType;
    }

    public void setFileSize(final long fileSize) {
        this.fileSize = fileSize;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof FileUploadResponse)) {
            return false;
        } else {
            FileUploadResponse other = (FileUploadResponse)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$fileName = this.getFileName();
                Object other$fileName = other.getFileName();
                if (this$fileName == null) {
                    if (other$fileName != null) {
                        return false;
                    }
                } else if (!this$fileName.equals(other$fileName)) {
                    return false;
                }

                Object this$fileDownloadUri = this.getFileDownloadUri();
                Object other$fileDownloadUri = other.getFileDownloadUri();
                if (this$fileDownloadUri == null) {
                    if (other$fileDownloadUri != null) {
                        return false;
                    }
                } else if (!this$fileDownloadUri.equals(other$fileDownloadUri)) {
                    return false;
                }

                Object this$fileType = this.getFileType();
                Object other$fileType = other.getFileType();
                if (this$fileType == null) {
                    if (other$fileType != null) {
                        return false;
                    }
                } else if (!this$fileType.equals(other$fileType)) {
                    return false;
                }

                if (this.getFileSize() != other.getFileSize()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FileUploadResponse;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        Object $fileDownloadUri = this.getFileDownloadUri();
        result = result * 59 + ($fileDownloadUri == null ? 43 : $fileDownloadUri.hashCode());
        Object $fileType = this.getFileType();
        result = result * 59 + ($fileType == null ? 43 : $fileType.hashCode());
        long $fileSize = this.getFileSize();
        result = result * 59 + (int)($fileSize >>> 32 ^ $fileSize);
        return result;
    }

    public String toString() {
        return "FileUploadResponse(fileName=" + this.getFileName() + ", fileDownloadUri=" + this.getFileDownloadUri() + ", fileType=" + this.getFileType() + ", fileSize=" + this.getFileSize() + ")";
    }

    public FileUploadResponse(final String fileName, final String fileDownloadUri, final String fileType, final long fileSize) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public FileUploadResponse() {
    }
}
