package com.cybersoft.festore.api;

import com.cybersoft.festore.payload.BaseResponse;
import com.cybersoft.festore.payload.response.ColorResponse;
import com.cybersoft.festore.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/color")
public class ApiColorController {

    @Autowired
    private ColorServiceImp colorServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllColor(){
        List<ColorResponse> list = colorServiceImp.getAllColor();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("");
        baseResponse.setData(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addColor(@RequestParam String name){
        boolean isSuccess = colorServiceImp.addColor(name);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Thêm thành công" : "Thêm thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateColor(@PathVariable int id,@RequestParam String name){
        boolean isSuccess = colorServiceImp.updateColor(id,name);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Cập nhật thành công" : "Cập nhật thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteColor(@PathVariable int id){
        boolean isSuccess = colorServiceImp.deleteColor(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
