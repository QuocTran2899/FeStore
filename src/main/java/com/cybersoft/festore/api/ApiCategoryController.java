package com.cybersoft.festore.api;

import com.cybersoft.festore.entity.CategoryEntity;
import com.cybersoft.festore.payload.BaseResponse;
import com.cybersoft.festore.payload.response.CategoryResponse;
import com.cybersoft.festore.service.imp.CategoryServiceimp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class ApiCategoryController {

    @Autowired
    private CategoryServiceimp categoryServiceimp;

    @GetMapping("")
    public ResponseEntity<?> getAllCategory(){
        List<CategoryResponse> list = categoryServiceimp.getAllCategory();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("");
        baseResponse.setData(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addCategory(@RequestParam String name){
        boolean isSuccess = categoryServiceimp.addCategory(name);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Thêm thành công" : "Thêm thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestParam String name){
        boolean isSuccess  = categoryServiceimp.updateCategory(id, name);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Cập nhật thành công" : "Cập nhật thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id){
        boolean isSuccess  = categoryServiceimp.deleteCategory(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
