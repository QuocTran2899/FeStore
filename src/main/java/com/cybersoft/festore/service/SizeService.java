package com.cybersoft.festore.service;

import com.cybersoft.festore.entity.CategoryEntity;
import com.cybersoft.festore.entity.SizeEntity;
import com.cybersoft.festore.payload.response.CategoryResponse;
import com.cybersoft.festore.payload.response.SizeResponse;
import com.cybersoft.festore.repository.SizeRepository;
import com.cybersoft.festore.service.imp.SizeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SizeService implements SizeServiceImp {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public boolean addSize(String name) {
        boolean isSuccess = false;
        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setName(name);
        try {
            sizeRepository.save(sizeEntity);
            isSuccess = true;
        }catch (Exception e){
            System.out.println("Thêm thất bại" +e.getLocalizedMessage());
        }
        return isSuccess;
    }

    @Override
    public List<SizeResponse> getAllSize() {
        List<SizeEntity> list = sizeRepository.findAll();
        List<SizeResponse> listResponse = new ArrayList<>();
        for (SizeEntity item: list){
            SizeResponse sizeResponse = new SizeResponse();
            sizeResponse.setId(item.getId());
            sizeResponse.setName(item.getName());
            listResponse.add(sizeResponse);
        }
        return listResponse;
    }

    @Override
    public boolean updateSize(int id, String name) {
        boolean isSuccess = false;
        Optional<SizeEntity> optionalSize = sizeRepository.findById(id);
        if (optionalSize.isPresent()){
            SizeEntity sizeEntity = optionalSize.get();
            sizeEntity.setName(name);
            try {
                sizeRepository.save(sizeEntity);
                isSuccess = true;
            } catch (Exception e){
                System.out.println("Cập nhật thất bại " + e.getLocalizedMessage());
            }
        }
        return isSuccess;
    }

    @Override
    public boolean deleteSize(int id) {
        boolean isSuccess = false;
        try {
            if (sizeRepository.existsById(id)){
                sizeRepository.deleteById(id);
                isSuccess = true;
            } else {
                System.out.println("Không tìm thấy Size id: " + id);
            }
        }catch (Exception e){
            System.out.println("Xóa thất bại"+e.getLocalizedMessage());
        }
        return isSuccess;
    }
}
