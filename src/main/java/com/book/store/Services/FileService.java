package com.book.store.Services;

//import org.aspectj.weaver.tools.cache.SimpleCacheFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {



    public String uploadFile(MultipartFile file, String path) throws IOException {

        String originalFileName = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileNameWithExetension = filename+extension;
        String fullPathWithFilename = path+fileNameWithExetension;

        if(extension.equalsIgnoreCase(".png")||extension.equalsIgnoreCase(".jpg")||extension.equalsIgnoreCase(".jpeg")){
            //saving File
            //File folder = new File(SimpleCacheFactory.path);
            //upload file
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFilename));

            return ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").toUriString()+fileNameWithExetension;

        }else {
            throw new RuntimeException("File With this "+extension+" format not allow");
        }

    }


    public  InputStream getResource(String path, String name) throws FileNotFoundException {

        String fullPath = path + File.separator + name;
        InputStream inputStream = new FileInputStream(fullPath);

        return inputStream;
   }
}
