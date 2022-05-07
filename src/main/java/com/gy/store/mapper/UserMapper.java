package com.gy.store.mapper;

import com.gy.store.controller.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

//用户模块的持久层接口
public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户数据
     * @return 受影响的行数（curd，根据返回值来判断是否执行成功）
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 如果找到对应的用户则返回这个用户的数据，如果没有则返回null值
     */
    User findByUsername(String username);

    /**
     *
     * @param uid
     * @param password
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     *
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     *
     * @param user
     * @return
     */
    Integer updateInfoByUid(User user);

    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);

}
