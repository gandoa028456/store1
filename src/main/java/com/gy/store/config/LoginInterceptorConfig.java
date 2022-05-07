package com.gy.store.config;

import com.gy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration//加载当前的拦截器并注册
public class LoginInterceptorConfig implements WebMvcConfigurer {




    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        HandlerInterceptor interceptor=new LoginInterceptor();
        //配置白名单：存放在一个list集合里
        List<String> patterns=new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");

        //完成拦截器注册
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);
        System.out.println("nihao");
        System.out.println("hot-fix nihaogandoa");




    }
}
