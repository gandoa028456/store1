package com.gy.store.controller;

import com.gy.store.controller.entity.Address;
import com.gy.store.service.IAddressService;
import com.gy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController{

    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){

        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);

        return new JsonResult<>(OK);

    }

}
