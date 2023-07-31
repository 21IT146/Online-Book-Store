package com.book.store.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadhelper {

    @Value("${book.image.path}")
    private String imageUploadPath;

    public final String UPLOAD_DIR="C:\\Users\\Nasir Sherasiya\\Spring Boot Project\\E_Book_Store\\images";
    //public final String UPLOAD_DIR=new ClassPathResource("images").getFile().getAbsolutePath();

    public FileUploadhelper() throws IOException {


    }

    public boolean uploadFIle(MultipartFile multipartFile)
    {
        boolean f=false;

        try{
            // One way
//            InputStream is=multipartFile.getInputStream();
//            byte data[]=new byte[is.available()];
//            is.read(data);
//
//            //write
//            FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+ File.separator+multipartFile.getOriginalFilename());
//            fos.write(data);
//
//            fos.flush();
//            fos.close();

            //second way shortcut
            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f=true;

        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
        return f;
    }

}
