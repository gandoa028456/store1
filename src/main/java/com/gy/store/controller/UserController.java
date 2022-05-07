package com.gy.store.controller;

import com.gy.store.controller.entity.User;
import com.gy.store.controller.ex.FileEmptyException;
import com.gy.store.controller.ex.FileSizeException;
import com.gy.store.controller.ex.FileStateException;
import com.gy.store.controller.ex.FileTypeException;
import com.gy.store.service.IUserService;
import com.gy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    /*
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){

        JsonResult<Void> result=new JsonResult<>();
        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        }catch (InsertException e) {
            result.setState(5000);
            result.setMessage("注册时产生未知异常");
        }
        return result;
    }
     */
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);

        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data=userService.login(username,password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        //获取session中绑定的数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(OK,data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(
                                       String oldPassword,
                                       String newPassword,HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
       User data= userService.getByUid(getuidFromSession(session));
        System.out.println(data);
       return new JsonResult<>(OK,data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){

        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);

        userService.changeInfo(uid,username,user);
        return new JsonResult<>(OK);

    }

    //设置上传文件的最大值
    public static final int AVATAR_MAX_SIZE = 10*1024*1024;

    //限制上传文件类型
    public static final List<String> AVATAR_TYPE =new ArrayList<>();

    static{
        AVATAR_TYPE.add("images/jpeg");
        AVATAR_TYPE.add("images/png");
        AVATAR_TYPE.add("images/bmp");
        AVATAR_TYPE.add("images/gif");
    }



    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file")
                                           MultipartFile file){
        //判断文件是否为空
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出限制");
        }
        String contentType=file.getContentType();
        if(AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }

        String parent=session.getServletContext().getRealPath("upload");

        //io流 File对象指向这个路径，File是否存在
        File dir=new File(parent);
        if(!dir.exists()){
            dir.mkdirs();
        }

        //获取到这个文件名称，UUID工具来将生成一个新的字符作为文件名
        String originalFilename = file.getOriginalFilename();

        int index=originalFilename.lastIndexOf(".");
        String suffix=originalFilename.substring(index);

        //GSJGLJ-DSFD-SGASG-SFSF.png
        String filename=UUID.randomUUID().toString().toUpperCase()+suffix;

        File dest =new File(dir,filename);
        //参数file中数据写入到这个空文件中
        try {
            file.transferTo(dest); //将file文件中的数据写入到dest文件中
        }catch(FileStateException e){
            throw new FileStateException("文件状态异常");
        }
        catch (IOException e) {
            throw new RuntimeException("文件读写异常");
        }

        Integer uid=getuidFromSession(session);
        String username=getUsernameFromSession(session);
        //返回头像的路径/upload/test.png
        String avatar="/upload/"+filename;
        System.out.println(avatar);
        userService.changeAvatar(uid,avatar,username);
        return new JsonResult<>(OK,avatar);
    }
}
