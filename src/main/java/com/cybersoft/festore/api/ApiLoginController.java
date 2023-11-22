package com.cybersoft.festore.api;

import com.cybersoft.festore.payload.BaseResponse;
import com.cybersoft.festore.payload.request.SignUpRequest;
import com.cybersoft.festore.service.imp.LoginServiceImp;
import com.cybersoft.festore.util.JwtHelper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class ApiLoginController {

    private Logger logger = LoggerFactory.getLogger(ApiLoginController.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginServiceImp loginServiceImp;

    @Autowired
    private JwtHelper jwtHelper;

    private Gson gson = new Gson();





    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String email,@RequestParam String password){
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println(secretString);
        try{
            UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(email,password);
            authenticationManager.authenticate(authen);
            logger.info("User '{}' logged in successfully.",email + " + "+ password);
            // Lấy danh sách role đã lưu từ Security context holder khi Authenmanager chứng thực thành công
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
            String jsonRole = gson.toJson(roles);
            String token = jwtHelper.generateToken(jsonRole);
            BaseResponse baseResponse = new BaseResponse(200, "Authentication successful", token);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (Exception e){
            logger.error("Login failed for user '{}'.", email + " + " + password, e);
            // Xử lý ngoại lệ và trả về phản hồi lỗi
            BaseResponse errorResponse = new BaseResponse(401, "Authentication failed", null);
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest signUpRequest){
        logger.info("info insert{}",signUpRequest);
        boolean isSuccess = loginServiceImp.insertUser(signUpRequest);
        BaseResponse baseResponse = new BaseResponse(200, isSuccess ? "SignUp successful" : "SignUp failed" , isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

}
