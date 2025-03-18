package com.app.awsweek5project.service;

import com.app.awsweek5project.AmazonS3.BucketsOperationService;
import com.app.awsweek5project.AmazonS3.S3Config;
import com.app.awsweek5project.DTO.ImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final BucketsOperationService bucketsOperationService;
    private final S3Config s3Config;

    public void  uploadImage(MultipartFile file, String key) throws IOException {
        bucketsOperationService.addObject(file, key);
    }

    public List<ImageDTO> listAll() {
        try {
            List<ImageDTO> s3Objects = bucketsOperationService.listAllObjects();
            if (s3Objects == null || s3Objects.isEmpty()) {
                return Collections.emptyList();
            }
            return s3Objects;

        } catch (Exception e) {
            System.err.println("Error listing S3 objects: " + e.getMessage());
            return Collections.emptyList();
        }
    }
    public  void deleteImage(String key){
 bucketsOperationService.removeObject(key);
    }
}

