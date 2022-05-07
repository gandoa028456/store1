package com.gy.store.service.impl;

import com.gy.store.controller.entity.User;
import com.gy.store.mapper.UserMapper;
import com.gy.store.service.IUserService;
import com.gy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username=user.getUsername();
        User result=userMapper.findByUsername(username);
        if(result!=null){
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //密码加密处理实现 md5
        String oldPassword=user.getPassword();
        String salt=UUID.randomUUID().toString().toUpperCase();
        String md5Password=getMD5Password(oldPassword,salt);
        user.setPassword(md5Password);
        user.setSalt(salt);

        user.setIsDelete(0);
        user.setCreatedUser(user.getCreatedUser());
        user.setModifiedUser(user.getModifiedUser());
        Date date=new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);




        Integer rows=userMapper.insert(user);
        if(rows!=1){
            throw new InsertException("在用户注册过程中产生了未知异常");
        }
    }

    @Override
    public User login(String username, String password) {
        //根据用户名称查询用户的数据是否存在，如果不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if(result==null){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        //检测用户密码是否匹配
        String oldPassword=result.getPassword();
        //将用户密码相同md5加密，先获取salt，
        String salt=result.getSalt();
        String newMD5Password=getMD5Password(password,salt);
        if(!newMD5Password.equals(oldPassword)){
            throw new UsernameNotFoundException("密码不正确");
        }

        //判断isDelete字段的值是否为1,为1表示被标记为删除
        if(result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户不存在");
        }

        //提升用户性能
        User user=new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }


    @Override
    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword) {
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        String oldMd5Password=getMD5Password(oldPassword,result.getSalt());
        if(!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("密码错误");
        }

        //将新的密码设置到数据库中，将新的密码加密再更新
        String newMd5Password=getMD5Password(newPassword,result.getSalt());
       Integer rows=userMapper.updatePasswordByUid(uid,newMd5Password,username,new Date());
       if(rows !=1){
            throw new UpdateException("更新数据产生未知的异常");
       }

    }
    private String getMD5Password(String password,String salt){
        for(int i=0;i<3;i++){
            //md5 3次加密
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }

        return password;
    }



    @Override
    public User getByUid(Integer uid) {
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }
        User user=new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender((result.getGender()));
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UsernameNotFoundException("用户数据不存在");
        }

        user.setUid(uid);
        //user.setUsername(username);
        user.setCreatedUser(username);
        user.setCreatedTime(new Date());

        Integer rows=userMapper.updateInfoByUid(user);
        if(rows !=1){
            throw new UpdateException("更新数据时产生未知异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
            User result = userMapper.findByUid(uid);
            if(result==null||result.getIsDelete()==1){
                throw new UsernameNotFoundException("用户数据不存在");
            }
            Integer rows=userMapper.updateAvatarByUid(uid,avatar,username,new Date());
            if(rows!=1){
                throw new UpdateException("更新用户头像产生未知异常");
            }
    }


}
