package com.app.awsweek5project.AmazonS3;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
@Configuration
public class S3Config {
    private  final Helper helper;

    public S3Config ( Helper helper){
         this.helper= helper;

    }

     @Bean
    public S3Client getS3Client (){
         AwsCredentials credentials = AwsBasicCredentials.create(helper.AccessKey(), helper.SecretKey());
         return  S3Client.builder().region(Region.US_EAST_1).credentialsProvider(StaticCredentialsProvider.create(credentials)).build();
     }

     @Bean
    public  Helper gethelper(){
        return  new Helper();

     }
}
