package com.cybersoft.festore.service;

import com.cybersoft.festore.service.imp.FileStorageServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

@Service
public class FileStorageService implements FileStorageServiceImp {

    @Value("${path.upload.file}")
    private String folderRoot;

    private Path root;

    @Override
    public boolean saveFile(MultipartFile file) {
        boolean isSuccess = false;
        try{
            root = Paths.get(folderRoot);
            if (!Files.exists(root)){
                Files.createDirectory(root);
            }
            Files.copy(file.getInputStream(),root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            isSuccess = true;
        }catch (Exception e){
            System.out.println("Lỗi " + e.getLocalizedMessage());
        }
        return isSuccess;
    }

    @Override
    public Resource loadFile(String fileName) {
        try{
            root = Paths.get(folderRoot);
            Path pathFile = root.resolve(fileName);
            Resource resource = new UrlResource(pathFile.toUri());
            if (resource.exists()){
                return resource;
            }
        }catch (Exception e){
            System.out.println("lỗi load file" + e.getLocalizedMessage());
        }

        return null;
    }

}
