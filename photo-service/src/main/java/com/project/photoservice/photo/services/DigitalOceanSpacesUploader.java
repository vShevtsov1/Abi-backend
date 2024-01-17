package com.project.photoservice.photo.services;

import com.project.photoservice.photo.data.photo;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Component
public class DigitalOceanSpacesUploader {

    @Value("${digitalocean.spaceName}")
    private String spaceName;

    private final S3Client s3Client;

    public DigitalOceanSpacesUploader(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadAndReturnUrl(MultipartFile file) {
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String folderId = generateRandomObjectKey();
        String originalObjectKey = folderId + "." + fileExtension;
        String compressedObjectKey = folderId + "_preview" + "." + fileExtension;

        String folderName = folderId;
        uploadFile(spaceName, folderName, originalObjectKey, file);
        uploadCompressedFile(spaceName, folderName, compressedObjectKey, file);

        return originalObjectKey;
    }

    private void uploadFile(String spaceName, String folderName, String objectKey, MultipartFile file) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(spaceName)
                    .key(folderName + "/" + objectKey)
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .contentType(file.getContentType())
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (S3Exception | IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadCompressedFile(String spaceName, String folderName, String objectKey, MultipartFile file) {
        try {
            String fileExtension = getFileExtension(file.getOriginalFilename());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnails.of(file.getInputStream())
                    .size(350, 200)
                    .outputFormat(fileExtension)
                    .toOutputStream(outputStream);

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(spaceName)
                    .key(folderName + "/" + objectKey)
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .contentType(file.getContentType())
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(outputStream.toByteArray()));
        } catch (S3Exception | IOException e) {
            e.printStackTrace();
        }
    }

    private String generateRandomObjectKey() {
        return UUID.randomUUID().toString();
    }

    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
    }

    public String uploadLogoAndReturnUrl(String companyName,MultipartFile file) {
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String originalObjectKey = companyName + "." + fileExtension;

        uploadFile(spaceName, "company_logos", originalObjectKey, file);
        return originalObjectKey;
    }

    public String uploadAvatarAndReturnUrl(MultipartFile file) {
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String originalObjectKey = UUID.randomUUID().toString() + "." + fileExtension;

        uploadFile(spaceName, "user_avatars", originalObjectKey, file);
        return originalObjectKey;
    }

    public photo uploadProjectAndReturnUrl(String projectId,MultipartFile file) {
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String fileId = generateRandomObjectKey();

        String originalObjectKey = fileId + "." + fileExtension;
        String previewObjectKey = fileId + "_preview." + fileExtension;

        uploadFile(spaceName, "projects/" + projectId, originalObjectKey, file);

        uploadCompressedFile(spaceName, "projects/" + projectId, previewObjectKey, file);

        String originalUrl = "https://files.abiproptech.info/projects/" + projectId + "/" + originalObjectKey;
        String previewUrl = "https://files.abiproptech.info/projects/" + projectId + "/" + previewObjectKey;
        return new photo(originalUrl, previewUrl);
    }
}
