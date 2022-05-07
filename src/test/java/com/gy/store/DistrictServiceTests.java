package com.gy.store;

import com.gy.store.controller.entity.Address;
import com.gy.store.controller.entity.District;
import com.gy.store.service.IAddressService;
import com.gy.store.service.IDistrictService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.List;

@SpringBootTest
class DistrictServiceTests {
    @Autowired//自动装配
    private IDistrictService districtService;

    @Test
    public void findByParent(){
        List<District> list=districtService.getByParent("86");
        for(District d:list){
            System.err.println(d);
        }

    }




}
