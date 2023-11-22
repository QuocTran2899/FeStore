package com.cybersoft.festore.service;

import com.cybersoft.festore.entity.RoleEntity;
import com.cybersoft.festore.entity.UserEntity;
import com.cybersoft.festore.payload.request.SignUpRequest;
import com.cybersoft.festore.repository.UserRepository;
import com.cybersoft.festore.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean insertUser(SignUpRequest signUpRequest) {
        boolean isSuccess = false;
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signUpRequest.getUserName());
        userEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userEntity.setEmail(signUpRequest.getEmail());
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1);
        userEntity.setRoleEntity(roleEntity);
        try {
            userRepository.save(userEntity);
            isSuccess = true;
        }catch (Exception e){
            System.out.println("Thêm thất bại "+e.getLocalizedMessage());
            isSuccess = false;
        }
        return isSuccess;
    }
}
