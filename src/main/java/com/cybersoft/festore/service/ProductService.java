package com.cybersoft.festore.service;

import com.cybersoft.festore.entity.CategoryEntity;
import com.cybersoft.festore.entity.ColorEntity;
import com.cybersoft.festore.entity.ProductEntity;
import com.cybersoft.festore.entity.SizeEntity;
import com.cybersoft.festore.payload.response.ProductResponse;
import com.cybersoft.festore.repository.ProductRepository;
import com.cybersoft.festore.service.imp.FileStorageServiceImp;
import com.cybersoft.festore.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    private FileStorageServiceImp fileStorageServiceImp;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public boolean addProduct(String name, MultipartFile file, double price, String description, int idColor, int idSize, int idCategory) throws IOException {
        boolean isSave = fileStorageServiceImp.saveFile(file);
        if (isSave){
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(name);
            productEntity.setImage(file.getOriginalFilename());
            productEntity.setPrice(price);
            productEntity.setDescription(description);

            ColorEntity colorEntity = new ColorEntity();
            colorEntity.setId(idColor);

            productEntity.setColorEntity(colorEntity);

            SizeEntity sizeEntity = new SizeEntity();
            sizeEntity.setId(idSize);

            productEntity.setSizeEntity(sizeEntity);

            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(idCategory);

            productEntity.setCategoryEntity(categoryEntity);
            productRepository.save(productEntity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Resource downloadProductFile(String tenFile) throws IOException {
        return fileStorageServiceImp.loadFile(tenFile);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductEntity> list = productRepository.findAll();
        List<ProductResponse> listResponse = new ArrayList<>();
        for (ProductEntity item: list){
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(item.getId());
            productResponse.setName(item.getName());
            productResponse.setImage(item.getImage());
            productResponse.setPrice(item.getPrice());
            listResponse.add(productResponse);
        }
        return listResponse;
    }

    @Override
    public List<ProductResponse> getProductByCategoryId(String host, int id) {
        List<ProductEntity> list = productRepository.findByCategoryEntity_Id(id);
        List<ProductResponse> listResponse = new ArrayList<>();
        for (ProductEntity item: list){
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(item.getId());
            productResponse.setName(item.getName());
            productResponse.setPrice(item.getPrice());
            productResponse.setImage(item.getImage());
            listResponse.add(productResponse);
        }
        return listResponse;
    }

    @Override
    public boolean updateProduct(int id, String name, MultipartFile file, double price, String description, int idColor, int idSize, int idCategory) throws IOException {
        boolean isSuccess = false;
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductEntity productEntity = optionalProduct.get();

            // Cập nhật thông tin sản phẩm
            productEntity.setName(name);
            productEntity.setPrice(price);
            productEntity.setDescription(description);

            // Cập nhật hình ảnh nếu có
            if (file != null && !file.isEmpty()) {
                boolean isSave = fileStorageServiceImp.saveFile(file);
                if (isSave) {
                    productEntity.setImage(file.getOriginalFilename());
                } else {
                    System.out.println("Lỗi: Không thể lưu file.");
                    return false;
                }
            }

            // Cập nhật màu sắc, kích thước và danh mục
            ColorEntity colorEntity = new ColorEntity();
            colorEntity.setId(idColor);
            productEntity.setColorEntity(colorEntity);

            SizeEntity sizeEntity = new SizeEntity();
            sizeEntity.setId(idSize);
            productEntity.setSizeEntity(sizeEntity);

            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(idCategory);
            productEntity.setCategoryEntity(categoryEntity);

            // Lưu sản phẩm cập nhật vào cơ sở dữ liệu
            try {
                productRepository.save(productEntity);
                isSuccess = true;
            } catch (Exception e) {
                System.out.println("Cập nhật thất bại " + e.getLocalizedMessage());
            }
        }
        return isSuccess;
    }

    @Override
    public boolean deteleProduct(int id) {
        boolean isSuccess = false;
        try {
            if (productRepository.existsById(id)){
                productRepository.deleteById(id);
                isSuccess = true;
            } else {
                System.out.println("Không tìm thấy product id: " + id);
            }
        }catch (Exception e){
            System.out.println("Xóa thất bại"+e.getLocalizedMessage());
        }
        return isSuccess;
    }
}
