package com.devjr.apiJWT.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.UUID;

@Component
public class Utileria {

    @Value("${path-img}")
    public String ruta;

    public String saveImage(MultipartFile img) throws IOException {

        if(img.isEmpty()){
            throw  new IOException("File is empty");
        }


        String nameFile = img.getOriginalFilename();
        String nameFileFilter = nameFile.replace(" ", "-");
        String nameFileFinal = UUID.randomUUID().toString() + nameFileFilter;
        try{
            File file= new File(ruta+nameFileFinal);
            img.transferTo(file);

        }catch (IOException e){
            System.out.println("Error uploading the file");
        }


        return nameFileFinal;
    }


}
