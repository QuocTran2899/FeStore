package com.cybersoft.festore.service;

import com.cybersoft.festore.entity.CartEntity;
import com.cybersoft.festore.entity.ProductEntity;
import com.cybersoft.festore.entity.UserEntity;
import com.cybersoft.festore.payload.request.CartRequest;
import com.cybersoft.festore.payload.response.CartResponse;
import com.cybersoft.festore.repository.CartRepository;
import com.cybersoft.festore.repository.ProductRepository;
import com.cybersoft.festore.repository.UserRepository;
import com.cybersoft.festore.service.imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartServiceImp {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean addProductIntoCart(CartRequest cartRequest) {
        CartEntity cartEntity = new CartEntity();
        Optional<ProductEntity> optionalProduct = productRepository.findById(cartRequest.getIdProduct());
        Optional<UserEntity> optionalUser = userRepository.findById(cartRequest.getIdUser());
        if (optionalProduct.isPresent() && optionalUser.isPresent()){
            cartEntity.setProductEntity(optionalProduct.get());
            cartEntity.setUserEntity(optionalUser.get());
            cartEntity.setQuantity(cartRequest.getQuantity());
            try {
                cartRepository.save(cartEntity);
                return true;
            } catch (Exception e){
                System.out.println("Error: " +e);
                return false;
            }
        }
        return false;
    }

    @Override
    public List<CartResponse> getCart(int idUser) {
        List<CartEntity> list = cartRepository.findAll();
        List<CartResponse> listResponse = new ArrayList<>();
        for(CartEntity item: list){
            if (item.getUserEntity().getId() == idUser){
                CartResponse cartTemp = new CartResponse();
                cartTemp.setCart(item.getId());
                cartTemp.setQuantity(item.getQuantity());
                cartTemp.setNameProduct(item.getProductEntity().getName());
                listResponse.add(cartTemp);
            }
        }
        return listResponse;
    }
}
