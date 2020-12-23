package com.example.restaurantapii.config;

import com.example.restaurantapii.auth.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("h2-console/**").permitAll();
        http.authorizeRequests().antMatchers("/person/login").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/carts/add","/product/{id}","/product/category/product","/product/category","/product/category/{id}","/category/","/table/list","/place/list","/waiter/list").access("hasAnyRole('USER','ADMIN')");
        http.authorizeRequests().antMatchers( "/product/**", "/carts/**","/category/**","/info/**","/table/**","/place/**","/waiter/**","/file/**","/person/control/**","/product/getPage","/product/getPageSlice").access("hasRole('ADMIN')");
        http.httpBasic();
        http.cors();
    }

}
