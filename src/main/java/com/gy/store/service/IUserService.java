package com.gy.store.service;

import com.gy.store.controller.entity.User;

public interface IUserService {
    void reg(User user);

    /**
     * 用户登录功能
     * @param username 用户名
     * @param password 用户密码
     * @return 当前匹配的用户数据，如果没有则返回null值
     */
    User login(String username,String password);

    void changePassword(Integer uid,String username,String oldPassword,String newPassword);

    User getByUid(Integer uid);

    void changeInfo(Integer uid,String username,User user);

    /**
     * 可以导接口文档
     * @param uid
     * @param avatar
     * @param username
     */
    void changeAvatar(Integer uid,
                      String avatar,
                      String username);
}
