package com.itgroup.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Service
public class AWSS3Service {

    private final AmazonS3 s3;
    private final String bucketName;

    @Autowired
    public AWSS3Service(AmazonS3 s3, @Value("${aws.s3.bucket}") String bucketName) {
        this.s3 = s3;
        this.bucketName = bucketName;
    }

    public String uploadImageAndGetUrl(MultipartFile file) {
        String extension = determineExtension(file.getContentType());

        String filename = generateUniqueFileName(extension);
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            s3.putObject(bucketName, filename, file.getInputStream(), metadata);

            return generateUrl(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUrl(String filename) {
        return s3.getUrl(bucketName, filename).toString();
    }

    private String generateUniqueFileName(String extension) {
        return UUID.randomUUID() + "." + extension;
    }

    private String determineExtension(String contentType) {
        return (contentType != null && contentType.contains("jpeg")) ? "jpg" : "png";
    }
}