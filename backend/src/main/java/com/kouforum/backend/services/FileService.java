package com.kouforum.backend.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kouforum.backend.configuration.KouForumProperties;

@Service
public class FileService {

    @Autowired
    KouForumProperties kouForumProperties;

    Tika tika = new Tika();

    public String saveBase64StringAsFile(String image) {
        String fileName = UUID.randomUUID().toString();
        Path path = getProfileImagePath(fileName);
        try {
            OutputStream outputStream = new FileOutputStream(path.toFile());
            outputStream.write(decodedImage(image));
            outputStream.close();
            return fileName;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String detectType(String value) {
        return tika.detect(decodedImage(value));
    }

    public byte[] decodedImage(String encodedImage) {
        return Base64.getDecoder().decode(encodedImage.split(",")[1]);
    }

    public void deleteProfileImage(String image) {
        if (image == null) {
            return;
        }
        Path path = getProfileImagePath(image);
        try {
            Files.deleteIfExists(path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private Path getProfileImagePath(String fileName) {
        return Paths.get(kouForumProperties.getStorage().getRoot(), kouForumProperties.getStorage().getProfile(),
                fileName);
    }
}
