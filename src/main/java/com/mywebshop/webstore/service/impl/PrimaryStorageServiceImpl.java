package com.mywebshop.webstore.service.impl;

import com.mywebshop.webstore.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PrimaryStorageServiceImpl implements StorageService {

    @Override
    public void store(MultipartFile file) {

        Path filepath = Paths.get("classpath:/static/images/", file.getOriginalFilename());
        try {
            file.transferTo(filepath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
