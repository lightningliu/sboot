package com.lkh.sboot.controller;

import com.lkh.sboot.common.AjaxJson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public AjaxJson login(HttpServletRequest request, String user_name, String user_pwd) throws Exception {
        AjaxJson j=new AjaxJson();
        boolean success=true;
        String msg="验证成功";
        UsernamePasswordToken token = new UsernamePasswordToken(user_name, user_pwd);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
        }catch (Exception exception){
            success=false;
            if (exception != null) {
                if (UnknownAccountException.class.isInstance(exception)) {
                    msg = "账户不存在或密码不正确";
                } else if (IncorrectCredentialsException.class.isInstance(exception)) {
                    msg = "账户不存在或密码不正确";
                } else {
                    msg = "其他异常";
                }
            }
        }

        j.setMsg(msg);
        j.setSuccess(success);
        return j;
    }


    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        SecurityUtils.getSubject().logout();
        return "/login.html";
    }

}
