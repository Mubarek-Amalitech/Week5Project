package com.app.awsweek5project.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.app.awsweek5project.DTO.ImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Component

public class BucketsOperationService {
    private final S3Client s3Client;
    private final Helper helper;

    public BucketsOperationService(S3Client s3Client , Helper helper ) {
        this.s3Client = s3Client;
        this.helper = helper;
        if (!bucketExists()) {
            s3Client.createBucket((request) -> request.bucket(helper.S3BucketName()));
        }
    }

    boolean bucketExists() {


        try {
            s3Client.headBucket((request) -> request.bucket(helper.S3BucketName()));
            return true;
        } catch (NoSuchBucketException e) {
            return false;
        }
    }

    public void  addObject(MultipartFile file, String key) throws IOException {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(helper.S3BucketName()).key(key).build();
        Optional<MultipartFile> files = Optional.ofNullable(file);


     files.ifPresentOrElse(f-> {
         try {
             s3Client.putObject(putObjectRequest, RequestBody.fromBytes(f.getBytes()));
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     },()-> {
      throw  new RuntimeException(" failed to find file");

     } );

    }

    public  void removeObject(String key) {
        DeleteObjectRequest deleteObjectRequest= DeleteObjectRequest.builder().bucket(helper.S3BucketName()).key(key).build();
        s3Client.deleteObject(deleteObjectRequest);
    }

    public List<ImageDTO> listAllObjects(){
        ListObjectsV2Request listObjectsRequest= ListObjectsV2Request.builder().bucket(helper.S3BucketName()).build();
        ListObjectsV2Response response = s3Client.listObjectsV2(listObjectsRequest);
        String region = Region.US_EAST_1.id();
        String url= "https://" + helper.S3BucketName() + ".s3." + region + ".amazonaws.com/";
        List<ImageDTO> ou=  response.contents() != null ? response.contents().stream().map(object-> new ImageDTO(object.key(),url+object.key())).toList(): Collections.emptyList();
        System.out.println(ou);
        return ou;
    }



}
