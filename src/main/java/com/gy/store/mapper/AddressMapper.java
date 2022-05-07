package com.gy.store.mapper;

import com.gy.store.controller.entity.Address;

//收货地址持久层的接口
public interface AddressMapper {
    /**
     *插入用户的收货地址数据
     * @param address
     * @return
     */
    Integer insert(Address address);

    /**
     *根据用户的id统计收货地址数量
     * @param uid
     * @return
     */
    Integer countByUid(Integer uid);
}
