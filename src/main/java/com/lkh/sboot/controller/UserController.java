package com.lkh.sboot.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lkh.sboot.common.AjaxJson;
import com.lkh.sboot.common.Guid;
import com.lkh.sboot.common.Utils;
import com.lkh.sboot.entity.User;
import com.lkh.sboot.service.DeptService;
import com.lkh.sboot.service.RoleService;
import com.lkh.sboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("userController")
public class UserController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    /*获取部门树形列表*/
    @RequestMapping("getDept_tree")
    @ResponseBody
    public Object getDept_tree(){
        return deptService.getDeptList_combotree("-1");
    }
    /*获取用户列表*/
    @RequestMapping("getUserList")
    @ResponseBody
/*
    @RequiresPermissions("userController:add")//权限管理;
*/
    public Object getUserList(HttpServletRequest request,int rows, int page){
        String user_name=request.getParameter("user_name");
        String dept_id=request.getParameter("dept_id");
        String user_realname=request.getParameter("user_realname");
        String user_phone=request.getParameter("user_phone");
        String user_sex=request.getParameter("user_sex");
        PageHelper.startPage(page,rows);
        Page<Map> pageinfo=(Page) userService.getList(dept_id,user_name,user_realname,user_phone,user_sex);
        return Utils.returnMap(pageinfo.getResult(),pageinfo.getTotal());
    }


    /*保存操作*/
    @RequestMapping("saveUser")
    @ResponseBody
    public AjaxJson saveUser(User user){
        AjaxJson ajaxJson=new AjaxJson();
        if(user.getDept_id()==null || "".equals(user.getDept_id()+"")){
            user.setDept_id("-1");
        }
        boolean result=false;
        if (user.getUser_id()==null ||  "".equals(user.getUser_id())){
            user.setUser_id(Guid.getGuid());
            result=userService.addUser(user);
        }else{
            result=userService.updateUser(user);
        }
        if(result){
            ajaxJson.setMsg("操作成功");
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }
    /*删除操作*/
    @RequestMapping("delUser")
    @ResponseBody
    public AjaxJson delUser(String user_id){
        AjaxJson ajaxJson=new AjaxJson();
        if(userService.delUser(user_id)){
            ajaxJson.setMsg("操作成功");
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }



    /*获取角色列表*/
    @RequestMapping("getRoleList")
    @ResponseBody
    public Object getRoleList(HttpServletRequest request){
        return roleService.getList();
    }
}
