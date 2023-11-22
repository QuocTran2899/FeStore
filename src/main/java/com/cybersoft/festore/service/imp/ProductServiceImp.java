package com.cybersoft.festore.service.imp;

import com.cybersoft.festore.payload.response.ProductResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductServiceImp {
    boolean addProduct(String name, MultipartFile file, double price, String description, int idColor, int idSize, int idCategory) throws IOException;
    Resource downloadProductFile(String tenFile) throws IOException;

    List<ProductResponse> getAllProduct();

    List<ProductResponse> getProductByCategoryId(String host, int id);

    boolean updateProduct(int id, String name, MultipartFile file, double price, String description, int idColor, int idSize, int idCategory) throws IOException;

    boolean deteleProduct(int id);
}
