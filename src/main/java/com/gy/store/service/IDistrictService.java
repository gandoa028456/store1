package com.gy.store.service;

import com.gy.store.controller.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     *
     * @param parent
     * @return
     */
    List<District> getByParent(String parent);
}
