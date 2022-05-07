package com.gy.store.controller;

import com.gy.store.controller.ex.*;
import com.gy.store.service.ex.*;
import com.gy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    public static final int OK=200;

    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    @ExceptionHandler({ServiceException.class, FileUploadException.class})//用于统一处理抛出的异常

    public JsonResult<Void> handlerException(Throwable e){
        JsonResult<Void> result=new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        }else if(e instanceof InsertException){
            result.setState(4001);
            result.setMessage("插入数据时产生的未知异常");
        }else if(e instanceof UsernameDuplicatedException){
            result.setState(4002);
            result.setMessage("用户数据不存在的异常");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("用户名密码错误的异常");
        }else if(e instanceof AddressCountLimitException){
            result.setState(5003);
            result.setMessage("用户的收货地址超出上限的异常");
        }else if(e instanceof FileEmptyException){
            result.setState(6001);
            result.setMessage("文件为空");
        }else if(e instanceof FileSizeException){
            result.setState(6002);
            result.setMessage("文件大小异常");
        }else if(e instanceof FileStateException){
            result.setState(6003);
            result.setMessage("文件状态异常");
        }else if(e instanceof FileUploadIOException){
            result.setState(6004);
            result.setMessage("文件上传异常");
        }
        return result;
    }


    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());

    }

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }



}
