package com.cybersoft.festore.service;

import com.cybersoft.festore.entity.ColorEntity;
import com.cybersoft.festore.entity.SizeEntity;
import com.cybersoft.festore.payload.response.ColorResponse;
import com.cybersoft.festore.repository.ColorRepository;
import com.cybersoft.festore.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColorService implements ColorServiceImp {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public boolean addColor(String name) {
        boolean isSuccess = false;
        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setName(name);
        try {
            colorRepository.save(colorEntity);
            isSuccess = true;
        }catch (Exception e){
            System.out.println("Thêm thất bại"+e.getLocalizedMessage());
        }
        return isSuccess;

    }

    @Override
    public List<ColorResponse> getAllColor() {
        List<ColorEntity> list = colorRepository.findAll();
        List<ColorResponse> listResponse = new ArrayList<>();
        for (ColorEntity item: list){
            ColorResponse colorResponse = new ColorResponse();
            colorResponse.setId(item.getId());
            colorResponse.setName(item.getName());
            listResponse.add(colorResponse);
        }
        return listResponse;
    }

    @Override
    public boolean updateColor(int id, String name) {
        boolean isSuccess = false;
        Optional<ColorEntity> optionalColor = colorRepository.findById(id);
        if (optionalColor.isPresent()){
            ColorEntity colorEntity = optionalColor.get();
            colorEntity.setName(name);
            try {
                colorRepository.save(colorEntity);
                isSuccess = true;
            } catch (Exception e){
                System.out.println("Cập nhật thất bại " + e.getLocalizedMessage());
            }
        }
        return isSuccess;
    }

    @Override
    public boolean deleteColor(int id) {
        boolean isSuccess = false;
        try {
            if (colorRepository.existsById(id)){
                colorRepository.deleteById(id);
                isSuccess = true;
            } else {
                System.out.println("Không tìm thấy color id: " + id);
            }
        }catch (Exception e){
            System.out.println("Xóa thất bại"+e.getLocalizedMessage());
        }
        return isSuccess;
    }
}
