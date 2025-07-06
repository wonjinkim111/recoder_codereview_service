package com.yaas.recodercodereviewservice.file;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "file"
)
public class FileUploadProperties {
    private String uploadDir;

    public FileUploadProperties() {
    }

    public String getUploadDir() {
        return this.uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
