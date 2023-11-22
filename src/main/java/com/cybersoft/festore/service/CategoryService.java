package com.cybersoft.festore.service;

import com.cybersoft.festore.entity.CategoryEntity;
import com.cybersoft.festore.payload.response.CategoryResponse;
import com.cybersoft.festore.repository.CategoryRepository;
import com.cybersoft.festore.service.imp.CategoryServiceimp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceimp {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean addCategory(String name) {
        boolean isSuccess = false;
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        try {
            categoryRepository.save(categoryEntity);
            isSuccess = true;
        }catch (Exception e){
            System.out.println("Thêm thất bại" +e.getLocalizedMessage());
        }
        return isSuccess;
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<CategoryEntity> list = categoryRepository.findAll();
        List<CategoryResponse> listResponse = new ArrayList<>();
        for (CategoryEntity item: list){
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(item.getId());
            categoryResponse.setName(item.getName());
            listResponse.add(categoryResponse);
        }
        return listResponse;
    }

    @Override
    public boolean updateCategory(int id, String name) {
        boolean isSuccess = false;
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            CategoryEntity categoryEntity = optionalCategory.get();
            categoryEntity.setName(name);
            try {
                categoryRepository.save(categoryEntity);
                isSuccess = true;
            } catch (Exception e){
                System.out.println("Cập nhật thất bại " + e.getLocalizedMessage());
            }
        }
        return isSuccess;
    }

    @Override
    public boolean deleteCategory(int id) {
        boolean isSuccess = false;
        try {
            if (categoryRepository.existsById(id)){
                categoryRepository.deleteById(id);
                isSuccess = true;
            } else {
                System.out.println("Không tìm thấy Category id: " + id);
            }
        }catch (Exception e){
            System.out.println("Xóa thất bại"+e.getLocalizedMessage());
        }
        return isSuccess;
    }
}
