package com.example.demo.util;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class HandleImage {

    private final AmazonS3 amazonS3;
    private final String bucketName;

    public HandleImage(AmazonS3 amazonS3, String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    public boolean isImage(String fileName) {
        String[] allowedExtensions = { "png", "jpeg", "jpg", "webp" };
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        for (String ext : allowedExtensions) {
            if (ext.equals(fileExtension)) {
                return true;
            }
        }
        return false;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        String uniqueFileName = "img_" + UUID.randomUUID().toString().substring(0, 8) + "." + fileExtension;
        amazonS3.putObject(new PutObjectRequest(bucketName, uniqueFileName, file.getInputStream(), null));
        return amazonS3.getUrl(bucketName, uniqueFileName).toString();
    }

    public void deleteImage(String filename) {
        amazonS3.deleteObject(bucketName, filename);
    }
}
