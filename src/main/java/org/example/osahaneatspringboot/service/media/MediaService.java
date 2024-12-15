package org.example.osahaneatspringboot.service.media;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    String uploadImage(MultipartFile file) throws IOException;

    Resource loadImage(String fileName) throws MalformedURLException;

    void deleteImage(String fileName) throws IOException;
}
