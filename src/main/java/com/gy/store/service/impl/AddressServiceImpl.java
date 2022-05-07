package com.gy.store.service.impl;

import com.gy.store.controller.entity.Address;
import com.gy.store.mapper.AddressMapper;
import com.gy.store.service.IAddressService;
import com.gy.store.service.ex.AddressCountLimitException;
import com.gy.store.service.ex.InsertException;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //调用收货地址统计的方法
        Integer count = addressMapper.countByUid(uid);
        if(count>=maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }

        //uid,isDelete
        address.setUid(uid);
        Integer isDefault =count==0?1:0;
        address.setIsDefault(isDefault);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        //插入收货地址的方法
        Integer rows=addressMapper.insert(address);
        if(rows !=1){
            throw new InsertException("插入用户的收货地址产生未知异常");
        }

    }
}
