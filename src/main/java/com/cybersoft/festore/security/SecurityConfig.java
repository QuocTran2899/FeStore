package com.cybersoft.festore.security;

import com.cybersoft.festore.filter.JwtFilter;
import com.cybersoft.festore.provider.CustomAuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    CustomAuthenProvider customAuthenProvider;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider)
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(corsConfigurationSource()).and()
                .authorizeHttpRequests()
                    .antMatchers("/login/**").permitAll()
                    .antMatchers("/index/**").permitAll()
                    .antMatchers("/cart/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/product/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/api/category/**").permitAll()
                    .antMatchers("/api/category/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET,"/api/size/**").permitAll()
                    .antMatchers("/api/size/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET,"/api/product/**").permitAll()
                    .antMatchers("/api/product/**").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET,"/api/color/**").permitAll()
                    .antMatchers("/api/color/**").hasRole("ADMIN")

                    .antMatchers("/api/cart/**").permitAll()
                    .anyRequest().authenticated()

                    .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
