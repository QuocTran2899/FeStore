package com.cybersoft.festore.api;

import com.cybersoft.festore.payload.BaseResponse;
import com.cybersoft.festore.payload.request.CartRequest;
import com.cybersoft.festore.payload.response.CartResponse;
import com.cybersoft.festore.service.imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ApiCartController {

    @Autowired
    private CartServiceImp cartServiceImp;

    @GetMapping("/{idUser}")
    public ResponseEntity<?> getCarts(@PathVariable int idUser){
        List<CartResponse> listResponse = cartServiceImp.getCart(idUser);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("");
        baseResponse.setData(listResponse);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addProductIntoCart(@RequestBody CartRequest cartRequest){
        boolean isSuccess = cartServiceImp.addProductIntoCart(cartRequest);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Thêm thành công" : "Thêm thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

}
