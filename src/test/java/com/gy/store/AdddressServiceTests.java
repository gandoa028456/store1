package com.gy.store;

import com.gy.store.controller.entity.Address;
import com.gy.store.service.IAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class AdddressServiceTests {
    @Autowired//自动装配
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address=new Address();
        address.setName("男朋友");
        address.setPhone("172656");
        addressService.addNewAddress(15,"guanliyuan",address);
    }




}
