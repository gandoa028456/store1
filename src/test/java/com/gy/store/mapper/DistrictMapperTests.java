package com.gy.store.mapper;

import com.gy.store.controller.entity.District;
import com.gy.store.controller.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {
    @Autowired
    private  DistrictMapper districtMapper;

    @Test
    public void findByParent (){
         List<District> list= districtMapper.findByParent("210100");
         for(District d:list){
             System.out.println(d);
         }

    }
}
