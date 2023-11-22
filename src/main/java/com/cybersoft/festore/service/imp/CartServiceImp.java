package com.cybersoft.festore.service.imp;

import com.cybersoft.festore.payload.request.CartRequest;
import com.cybersoft.festore.payload.response.CartResponse;

import java.util.List;

public interface CartServiceImp {
    boolean addProductIntoCart(CartRequest cartRequest);

    List<CartResponse> getCart(int idUser);
}
