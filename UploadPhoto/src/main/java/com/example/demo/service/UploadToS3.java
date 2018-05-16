package com.example.demo.service;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class UploadToS3 {
	
	@Value("#{environment.AWS_ACCESS_KEY_ID}")
	String key;
	
	@Value("#{environment.AWS_SECRET_ACCESS_KEY}")
	String skey;

	public String upload(String fileName,InputStream fs) {
		String fileURI;
		BasicAWSCredentials cred = new BasicAWSCredentials(key,
				skey);

		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred))
				.withRegion(Regions.US_WEST_2).build();
		
		PutObjectRequest putReq = new PutObjectRequest("pp134", fileName,
					fs, new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead);

			s3client.putObject(putReq);
		 fileURI = "http://" + "pp134" + ".s3.amazonaws.com/" + fileName;
			
		return fileURI;
		
	}

	
	}
