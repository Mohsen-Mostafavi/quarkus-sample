package com.mohsenmostafavi.reource.service;


import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@ApplicationScoped
public class FileService {

    @Inject
    MinioClient minioClient;


    public void createFile(InputStream inputStream) throws ServerException, InsufficientDataException, ErrorResponseException,
            IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String BUCKET_NAME = "book";
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(BUCKET_NAME).build();
        if(!minioClient.bucketExists(bucketExistsArgs)) {
            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(BUCKET_NAME).build();
            minioClient.makeBucket(makeBucketArgs);
        }
        PutObjectArgs putObjectArgs = PutObjectArgs.builder().contentType(MediaType.TEXT_PLAIN).bucket("book").stream(inputStream, 712, 100000000).object("name").build();
        minioClient.putObject(putObjectArgs);
    }




}
