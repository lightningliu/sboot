package com.lkh.sboot.controller;

import com.lkh.sboot.common.AjaxJson;
import com.lkh.sboot.common.Guid;
import com.lkh.sboot.common.Utils;
import com.lkh.sboot.entity.Menu;
import com.lkh.sboot.entity.Permission;
import com.lkh.sboot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/*菜单模块*/
@Controller
@RequestMapping("menuController")
public class MenuController {

    @Autowired
    private MenuService menuService;


    /*获取列表*/
    @RequestMapping("getList")
    @ResponseBody
    public Object getList_gridtree(HttpServletRequest request){
        return menuService.getList_gridtree("-1");
    }
    /*获取菜单列表-combotree*/
    @RequestMapping("getMenuList_combotree")
    @ResponseBody
    public Object getMenuList_combotree(HttpServletRequest request){
        return menuService.getMenuList_combotree("-1");
    }
    /*获取权限列表*/
    @RequestMapping("getPermissionList")
    @ResponseBody
    public Object getPermissionList(HttpServletRequest request,int rows, int page){
        int offset = (page-1)*rows;
        String menu_id=request.getParameter("menu_id");
        List<Map<String,Object>> list= menuService.getPermissionList(offset,rows,menu_id);
        int count=menuService.getPermissionListCount(menu_id);
        return Utils.returnMap(list,count);
    }
    /*保存菜单操作*/
    @RequestMapping("saveMenu")
    @ResponseBody
    public AjaxJson saveMenu(Menu menu){
        AjaxJson ajaxJson=new AjaxJson();
        if(menu.getParent_menu_id()==null || "".equals(menu.getParent_menu_id()+"")){
            menu.setParent_menu_id("-1");
        }
        boolean result=false;
        if (menu.getMenu_id()==null ||  "".equals(menu.getMenu_id())){
            menu.setMenu_id(Guid.getGuid());
            result=menuService.addMenu(menu);
        }else{
            result=menuService.updateMenu(menu);
        }
        if(result){
            ajaxJson.setMsg("操作成功");
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }
    /*删除菜单操作*/
    @RequestMapping("delMenu")
    @ResponseBody
    public AjaxJson delMenu(String menu_id){
        AjaxJson ajaxJson=new AjaxJson();
        if(menuService.delMenu(menu_id)){
            ajaxJson.setMsg("操作成功");
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }

    /*保存权限操作*/
    @RequestMapping("savePerm")
    @ResponseBody
    public AjaxJson savePerm(Permission permission){
        AjaxJson ajaxJson=new AjaxJson();
        boolean result=false;
        if (permission.getPerm_id()==null || "".equals(permission.getPerm_id())){
            permission.setPerm_id(Guid.getGuid());
            result=menuService.addPermission(permission);
        }else{
            result=menuService.updatePermission(permission);
        }
        if(result){
            ajaxJson.setMsg("操作成功");
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }
    /*删除权限操作*/
    @RequestMapping("delPerm")
    @ResponseBody
    public AjaxJson delPerm(String perm_id){
        AjaxJson ajaxJson=new AjaxJson();
        if(menuService.delPermission(perm_id)){
            ajaxJson.setMsg("操作成功");
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }

}
