package com.cybersoft.festore.service.imp;


import com.cybersoft.festore.payload.request.SignUpRequest;
import org.springframework.stereotype.Service;


public interface LoginServiceImp {

    boolean insertUser(SignUpRequest signUpRequest);
}
