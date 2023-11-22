package com.cybersoft.festore.service.imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileStorageServiceImp {
    boolean saveFile(MultipartFile file) throws IOException;
    Resource loadFile(String fileName) throws IOException;
}
