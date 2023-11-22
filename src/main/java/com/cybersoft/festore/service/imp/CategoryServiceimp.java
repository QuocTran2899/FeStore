package com.cybersoft.festore.service.imp;

import com.cybersoft.festore.payload.response.CategoryResponse;

import java.util.List;

public interface CategoryServiceimp {

    boolean addCategory(String name);
    List<CategoryResponse> getAllCategory();
    boolean updateCategory(int id,String name);
    boolean deleteCategory(int id);

}
