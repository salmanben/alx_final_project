package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.AwsS3SettingsDto;
import com.example.demo.model.AwsS3Settings;
import com.example.demo.repository.AwsS3SettingsRepository;
import java.util.List;

@Service
public class AwsS3SettingsService {
    
    private final AwsS3SettingsRepository AwsS3SettingsRepository;

    public AwsS3SettingsService(AwsS3SettingsRepository AwsS3SettingsRepository) {
        this.AwsS3SettingsRepository = AwsS3SettingsRepository;
    }


    public AwsS3SettingsDto getAwsS3Settings(){
       List<AwsS3Settings> ListAwsS3Settings = AwsS3SettingsRepository.findAll();
        if (ListAwsS3Settings.size() == 0)
            return new AwsS3SettingsDto("", "", "", "");
        AwsS3Settings AwsS3Settings = ListAwsS3Settings.get(0);
        return new AwsS3SettingsDto(AwsS3Settings.getAccessKey(), AwsS3Settings.getSecretKey(), AwsS3Settings.getRegion(), AwsS3Settings.getBucketName());
    }

    public void updateAwsS3Settings(AwsS3SettingsDto AwsS3SettingsDto){
        List<AwsS3Settings> ListAwsS3Settings = AwsS3SettingsRepository.findAll();
        if (ListAwsS3Settings.size() == 0) {
            AwsS3Settings awsS3Settings = new AwsS3Settings();
            awsS3Settings.setAccessKey(AwsS3SettingsDto.getAccessKey());
            awsS3Settings.setSecretKey(AwsS3SettingsDto.getSecretKey());
            awsS3Settings.setRegion(AwsS3SettingsDto.getRegion());
            awsS3Settings.setBucketName(AwsS3SettingsDto.getBucketName());
            AwsS3SettingsRepository.save(awsS3Settings);
            return;
        }
        AwsS3Settings AwsS3Settings = AwsS3SettingsRepository.findAll().get(0);
        
        AwsS3Settings.setAccessKey(AwsS3SettingsDto.getAccessKey());
        AwsS3Settings.setSecretKey(AwsS3SettingsDto.getSecretKey());
        AwsS3Settings.setRegion(AwsS3SettingsDto.getRegion());
        AwsS3Settings.setBucketName(AwsS3SettingsDto.getBucketName());
        AwsS3SettingsRepository.save(AwsS3Settings);
        
    }
}
