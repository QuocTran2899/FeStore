package com.cybersoft.festore.service.imp;

import com.cybersoft.festore.payload.response.CategoryResponse;
import com.cybersoft.festore.payload.response.SizeResponse;

import java.util.List;

public interface SizeServiceImp {
    boolean addSize(String name);
    List<SizeResponse> getAllSize();
    boolean updateSize(int id,String name);
    boolean deleteSize(int id);
}
