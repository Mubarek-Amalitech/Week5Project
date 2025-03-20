package com.app.awsweek5project.AmazonS3;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

@Getter
public class Helper {
    private  final String HTTPS= "https://";
    private  final String domain= ".amazonaws.com";

    private  final String S3= ".s3.";

    public String S3BucketName(){
        return  "awsweek5labbucket";

               // s3bucketName;
    }

    public  String AccessKey(){
        return  "AKIAXTORPCBHGZDTXKMS";
    }

    public  String SecretKey(){
        return  "NdCQ68wJHdUy3nHTviqdcDJ39TTGuRZDBCBFEQ88";
    }

}