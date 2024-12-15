package org.example.osahaneatspringboot.service.media;

import static org.example.osahaneatspringboot.constant.code.MediaConstant.SUPPORTED_IMAGE_FORMATS;
import static org.example.osahaneatspringboot.constant.message.MediaErrorMessage.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.example.osahaneatspringboot.constant.error.MediaErrorCode;
import org.example.osahaneatspringboot.exception.ApiException;
import org.example.osahaneatspringboot.exception.ValidateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MediaServiceImpl implements MediaService {
    @Value("${upload.max-file-size}")
    Long maxFileSize;

    @Value("${upload.path}")
    String uploadPath;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            Map<String, String> error = Map.of("file", IMAGE_EMPTY);
            throw new ValidateException(error);
        }
        String contentType = file.getContentType();
        if (contentType == null || !SUPPORTED_IMAGE_FORMATS.contains(contentType)) {
            Map<String, String> error = Map.of("file", INVALID_IMAGE_FORMAT);
            throw new ValidateException(error);
        }

        if (file.getSize() > maxFileSize) {
            Map<String, String> error = Map.of("file", IMAGE_TOO_LARGE);
            throw new ValidateException(error);
        }

        Path uploadDir = Paths.get(uploadPath);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadDir.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }

    @Override
    public Resource loadImage(String fileName) throws MalformedURLException {
        Path filePath = Paths.get(uploadPath).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        }
        throw new ApiException(MediaErrorCode.IMAGE_LOAD_FAILED);
    }

    @Override
    public void deleteImage(String fileName) throws IOException {
        Path filePath = Paths.get(uploadPath).resolve(fileName);

        if (!Files.exists(filePath)) {
            throw new ApiException(MediaErrorCode.IMAGE_NOT_FOUND);
        }

        Files.delete(filePath);
    }
}
