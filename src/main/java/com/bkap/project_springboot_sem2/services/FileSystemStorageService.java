package com.bkap.project_springboot_sem2.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService{
    private final Path rootLocatino;
    public FileSystemStorageService(){
        this.rootLocatino = Paths.get("src/main/resources/static/uploads");

    }

    @Override
    public void store(MultipartFile file) {
        System.out.println("1111");

        try {
            Path destinationFile = this.rootLocatino.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            System.out.println(file.getOriginalFilename());
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocatino);
            System.out.println("2222");
        }catch (IOException e){
            e.printStackTrace();

        }

    }
}
