package com.gy.store.mapper;

import com.gy.store.controller.entity.Address;
import com.gy.store.controller.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    private  AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address=new Address();
        address.setUid(15);
        address.setName("女朋友");
        address.setPhone("1414324");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer count=addressMapper.countByUid(15);
        System.out.println(count);
    }



}
