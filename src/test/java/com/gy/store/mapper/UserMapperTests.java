package com.gy.store.mapper;

import com.gy.store.controller.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private  UserMapper userMapper;

    @Test
    public void insert(){
        User user=new User();
        user.setUsername("zengxiaoyong");
        user.setPassword("123");
        Integer rows= userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername(){
        User user=userMapper.findByUsername("zengxiaoyong");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(9,"654820","系统管理员",new Date());
    }


    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(9));
    }

    @Test
    public void updateInfoByUid(){
        User user=new User();
        user.setUid(13);
        user.setPhone("15223527644");
        user.setEmail("1163858423@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(15,"/upload/avator.jpg","baba",new Date());
    }


}
