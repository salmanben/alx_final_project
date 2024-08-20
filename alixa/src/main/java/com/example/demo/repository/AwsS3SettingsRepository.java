package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AwsS3Settings;

public interface AwsS3SettingsRepository extends JpaRepository<AwsS3Settings, Integer> {

    
}