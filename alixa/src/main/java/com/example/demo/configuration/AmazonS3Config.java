package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.example.demo.DTO.AwsS3SettingsDto;
import com.example.demo.model.AwsS3Settings;
import com.example.demo.repository.AwsS3SettingsRepository;
import com.example.demo.service.AwsS3SettingsService;


@Configuration
public class AmazonS3Config {

    private final AwsS3SettingsService awsS3SettingsService;
    public AmazonS3Config(AwsS3SettingsService awsS3SettingsService) {
        this.awsS3SettingsService = awsS3SettingsService;
    }


    @Bean
    public AmazonS3 amazonS3() {
        AwsS3SettingsDto awsS3Settings = awsS3SettingsService.getAwsS3Settings();
        System.out.println("Access Key: " + awsS3Settings.getAccessKey());
        AWSCredentials credentials = new BasicAWSCredentials(awsS3Settings.getAccessKey(), awsS3Settings.getSecretKey());
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(awsS3Settings.getRegion())
                .build();

        return s3client;
    }

    @Bean
    public String getBucketName() {
        AwsS3SettingsDto awsS3Settings = awsS3SettingsService.getAwsS3Settings();
        return awsS3Settings.getBucketName();
    }
}