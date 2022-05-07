package com.gy.store.service.impl;

import com.gy.store.controller.entity.District;
import com.gy.store.mapper.DistrictMapper;
import com.gy.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list=districtMapper.findByParent(parent);
        for(District d:list){
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }
}
