package com.app.awsweek5project.AmazonS3;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Helper {

    @Value("${aws.s3-bucket}")
    private  String s3bucketName;
    @Value("${aws.access-key}")
    String accessKey;
    @Value("${aws.secret-key}")
    String secretKey;

    public String S3BucketName(){
        return s3bucketName;
    }

    public  String AccessKey(){
        return  accessKey;
    }

    public  String SecretKey(){
        return secretKey;
    }

}