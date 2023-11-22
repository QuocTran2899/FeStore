package com.cybersoft.festore.filter;

import com.cybersoft.festore.util.JwtHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


//Tạo filter để hứng tken mỗi khi người dùng gọi request
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    private Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Lấy token mà client truyền trên header(Authorization)
        String headerValues = request.getHeader("Authorization");
        System.out.println(headerValues);
        if(headerValues != null && headerValues.startsWith("Bearer ")){
            //Cắt chữ Bearer để lấy token
            String token = headerValues.substring(7);
            String data = jwtHelper.parseToken(token);
            System.out.println("Kiem tra "+data);
            if(data!= null && !data.isEmpty()){
                //Chứng thực Hợp lệ, tạo chứng thực cho Security
                Type listType = new TypeToken<ArrayList<SimpleGrantedAuthority>>(){}.getType();
                List<SimpleGrantedAuthority> roles = gson.fromJson(data,listType);

//                gson.fromJson(data);
//                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
//                roles.add(grantedAuthority);

                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("","",roles);
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(user);
            } else {
                //Không hợp lệ
                System.out.println("Header không hợp lệ");
            }

        }

        filterChain.doFilter(request,response);

    }
}
