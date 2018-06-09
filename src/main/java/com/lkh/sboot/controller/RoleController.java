package com.lkh.sboot.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lkh.sboot.common.AjaxJson;
import com.lkh.sboot.common.Guid;
import com.lkh.sboot.common.Utils;
import com.lkh.sboot.entity.Role;
import com.lkh.sboot.entity.RoleMenu;
import com.lkh.sboot.entity.RolePermission;
import com.lkh.sboot.service.PermissionService;
import com.lkh.sboot.service.RoleMenuService;
import com.lkh.sboot.service.RolePermissionService;
import com.lkh.sboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 角色权限模块
 */
@Controller
@RequestMapping("roleController")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RolePermissionService rolePermissionService;

    /*获取角色列表*/
    @RequestMapping("getRoleList")
    @ResponseBody
    public Object getRoleList(HttpServletRequest request, int rows, int page){
        PageHelper.startPage(page,rows);
        Page<Map> pageinfo=(Page) roleService.getList();
        return Utils.returnMap(pageinfo.getResult(),pageinfo.getTotal());
    }


    /*保存操作*/
    @RequestMapping("saveRole")
    @ResponseBody
    public AjaxJson saveRole(Role role){
        AjaxJson ajaxJson=new AjaxJson();
        boolean result=false;
        if (role.getRole_id()==null ||  "".equals(role.getRole_id())){
            role.setRole_id(Guid.getGuid());
            result=roleService.addRole(role);
        }else{
            result=roleService.updateRole(role);
        }
        if(result){
            ajaxJson.setMsg("操作成功");
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }
    /*删除操作*/
    @RequestMapping("delRole")
    @ResponseBody
    public AjaxJson delRole(String role_id){
        AjaxJson ajaxJson=new AjaxJson();
        if(roleService.delRole(role_id)){
            ajaxJson.setMsg("操作成功");
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }



    @RequestMapping("getMenuAndPerssionList_tree")
    @ResponseBody
    public Object getMenuAndPerssionList_tree(HttpServletRequest request){
        String role_id=request.getParameter("role_id");
        System.out.println("==="+role_id);
        return permissionService.getMenuAndPerssionList_tree(role_id,"-1");
    }

    /*保存操作*/
    @RequestMapping("savePermission")
    @ResponseBody
    public AjaxJson savePermission(HttpServletRequest request){
        String role_id=request.getParameter("role_id");
        String menus=request.getParameter("menus");
        String permissions=request.getParameter("permissions");
        AjaxJson ajaxJson=new AjaxJson();
        String[] menuArray=menus.split(",");
        String[] permissionArray=permissions.split(",");
        boolean result=true;
        //删除该角色所有菜单
        roleMenuService.delRoleMenuByRoleId(role_id);
        for (int i = 0; i < menuArray.length; i++) {
            RoleMenu roleMenu=new RoleMenu();
            roleMenu.setMenu_id(menuArray[i]);
            roleMenu.setRole_id(role_id);
            if (!roleMenuService.addRoleMenu(roleMenu)){
                result = false;
            }
        }
        //删除该角色所有权限
        rolePermissionService.delRolePermissionByRoleId(role_id);
        for (int j = 0; j < permissionArray.length; j++) {
            RolePermission rolePermission=new RolePermission();
            rolePermission.setPerm_id(permissionArray[j]);
            rolePermission.setRole_id(role_id);
            if (!rolePermissionService.addRolePermission(rolePermission)){
                result = false;
            }
        }
        if(result){
            ajaxJson.setMsg("操作成功");
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }
}
