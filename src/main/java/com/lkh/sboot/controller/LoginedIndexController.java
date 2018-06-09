package com.lkh.sboot.controller;

import com.lkh.sboot.entity.User;
import com.lkh.sboot.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/*登录成功后首页*/
@Controller
@RequestMapping("loginedIndexController")
public class LoginedIndexController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("index")
    public String index(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        User userinfo=(User) subject.getPrincipal();
        request.getSession().setAttribute("userinfo",userinfo);

        return "index";
    }

    /**
     * 获取当前用户的菜单列表
     * @param request
     * @return
     */
    @RequestMapping("getMenuList")
    @ResponseBody
    public Object getMenuList(HttpServletRequest request){
        User userinfo=(User)request.getSession().getAttribute("userinfo");
        String role_id="";
        if (userinfo!=null){
            role_id=userinfo.getRole_id();
        }
        return menuService.getMenuListByRole_tree("-1",role_id);
    }
}
