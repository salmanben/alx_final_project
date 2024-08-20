package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AwsS3SettingsDto {

    private int id;
    @NotBlank(message = "Access key is required")
    @Size(max = 255, message = "Access key is too long")
    private String accessKey;
    @Size(max = 255, message = "Secret key is too long")
    @NotBlank(message = "Secret key is required")
    private String secretKey;
    @Size(max = 255, message = "Region is too long")
    @NotBlank(message = "Region is required")
    private String region;
    @Size(max = 255, message = "Bucket name is too long")
    @NotBlank(message = "Bucket name is required")
    private String bucketName;

    private AwsS3SettingsDto() {
    }

    public AwsS3SettingsDto(String accessKey, String secretKey, String region, String bucketName) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.region = region;
        this.bucketName = bucketName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
