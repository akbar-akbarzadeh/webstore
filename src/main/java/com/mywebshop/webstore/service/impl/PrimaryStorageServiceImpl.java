package com.mywebshop.webstore.service.impl;

import com.mywebshop.webstore.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PrimaryStorageServiceImpl implements StorageService {

    @Override
    public void store(MultipartFile file) {

        System.out.println(file.getOriginalFilename());
        Path filepath = Paths.get("E:\\securityproject\\webstore\\src\\main\\resources\\static\\images", file.getOriginalFilename());
        File newFile = new File(filepath.toString());
        try {
            file.transferTo(newFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
