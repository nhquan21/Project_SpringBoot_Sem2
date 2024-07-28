package com.bkap.project_springboot_sem2.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface StorageService {
    void init();
    void store(MultipartFile file);
}
