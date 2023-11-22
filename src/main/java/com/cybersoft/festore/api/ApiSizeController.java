package com.cybersoft.festore.api;

import com.cybersoft.festore.payload.BaseResponse;
import com.cybersoft.festore.payload.response.CategoryResponse;
import com.cybersoft.festore.payload.response.SizeResponse;
import com.cybersoft.festore.service.imp.SizeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/size")
public class ApiSizeController {

    @Autowired
    private SizeServiceImp sizeServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllSize(){
        List<SizeResponse> list = sizeServiceImp.getAllSize();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("");
        baseResponse.setData(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<?> addSize(@RequestParam String name){
        boolean isSuccess = sizeServiceImp.addSize(name);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Thêm thành công" : "Thêm thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSize(@PathVariable int id, @RequestParam String name){
        boolean isSuccess  = sizeServiceImp.updateSize(id, name);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Cập nhật thành công" : "Cập nhật thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSize(@PathVariable int id){
        boolean isSuccess  = sizeServiceImp.deleteSize(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
