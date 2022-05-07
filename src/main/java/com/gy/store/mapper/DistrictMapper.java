package com.gy.store.mapper;

import com.gy.store.controller.entity.District;

import java.util.List;

public interface DistrictMapper {

    /**
     *
     * @param integer
     * @return
     */
    List<District>findByParent(String parent);
}
