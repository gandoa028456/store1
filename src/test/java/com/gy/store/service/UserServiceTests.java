package com.gy.store.service;

import com.gy.store.controller.entity.User;
import com.gy.store.service.ex.ServiceException;
import com.gy.store.service.ex.UpdateException;
import com.gy.store.service.ex.UsernameNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("lisi");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());

            System.out.println(e.getMessage());
        }

    }

    @Test
    public void login() {
        try {
            User user=userService.login("gandoa1","028456");
            System.out.println(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void changePassword(){
        try {
            userService.changePassword(11,"管理员","113","321");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getByUid() {
        System.err.println(userService.getByUid(15));
    }

    @Test
    public void changeInfo() {
        User user=new User();
        user.setPhone("13996656132");
        user.setEmail("yuan@qq.com");
        user.setGender(0);
        userService.changeInfo(9,"管理员",user);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(15,"/upload/test.jpg","小明");
    }

}
