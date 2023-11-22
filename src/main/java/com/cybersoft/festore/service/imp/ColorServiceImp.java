package com.cybersoft.festore.service.imp;

import com.cybersoft.festore.payload.response.ColorResponse;

import java.util.List;

public interface ColorServiceImp {
    boolean addColor(String name);

    List<ColorResponse> getAllColor();

    boolean updateColor(int id,String name);

    boolean deleteColor(int id);


}
