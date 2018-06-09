package com.lkh.sboot.service;

import com.lkh.sboot.entity.Menu;
import com.lkh.sboot.entity.Permission;
import com.lkh.sboot.mapper.MenuMapper;
import com.lkh.sboot.mapper.PermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private PermissionMapper permissionMapper;
    public List<Map<String,Object>> getList_gridtree(String parent_menu_id){
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list= menuMapper.getListByParentMenuId(parent_menu_id);
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map=list.get(i);
            List<Map<String,Object>> list1=menuMapper.getListByParentMenuId(map.get("menu_id")+"");
            if(list1.size()>0){//说明有子节点
                map.put("children",this.getList_gridtree(map.get("menu_id")+""));
            }
            result.add(map);
        }
        return result;
    }

    public List<Map<String,Object>> getMenuList_combotree(String parent_menu_id){
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list= menuMapper.getListByParentMenuId(parent_menu_id);
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map=new HashMap<String,Object>();
            List<Map<String,Object>> list1=menuMapper.getListByParentMenuId(list.get(i).get("menu_id")+"");
            if(list1.size()>0){//说明有子节点
                map.put("children",this.getMenuList_combotree(list.get(i).get("menu_id")+""));
            }
            map.put("id",list.get(i).get("menu_id")+"");
            map.put("text",list.get(i).get("menu_name")+"");
            result.add(map);
        }
        return result;
    }

    public List<Map<String,Object>> getMenuListByRole_tree(String parent_menu_id,String role_id){
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list= menuMapper.getListByParentMenuIdAndRole(parent_menu_id,role_id);
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map=new HashMap<String,Object>();
            List<Map<String,Object>> list1=menuMapper.getListByParentMenuIdAndRole(list.get(i).get("menu_id")+"",role_id);
            if(list1.size()>0){//说明有子节点
                map.put("children",this.getMenuListByRole_tree(list.get(i).get("menu_id")+"",role_id));
            }
            map.put("id",list.get(i).get("menu_id")+"");
            map.put("text",list.get(i).get("menu_name")+"");
            map.put("attributes",list.get(i));
            if("1".equals(list.get(i).get("checked"))){
                result.add(map);
            }else {
                System.out.println(list.get(i).get("menu_name")+"");
            }
        }
        return result;
    }

    public List<Map<String,Object>> getMenuList_tree(String parent_menu_id){
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list= menuMapper.getListByParentMenuId(parent_menu_id);
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map=new HashMap<String,Object>();
            List<Map<String,Object>> list1=menuMapper.getListByParentMenuId(list.get(i).get("menu_id")+"");
            if(list1.size()>0){//说明有子节点
                map.put("children",this.getMenuList_tree(list.get(i).get("menu_id")+""));
            }
            map.put("id",list.get(i).get("menu_id")+"");
            map.put("text",list.get(i).get("menu_name")+"");
            map.put("attributes",list.get(i));
            result.add(map);
        }
        return result;
    }


    public List<Map<String,Object>> getPermissionList(int offset,int rows,String menu_id){
        return permissionMapper.getPermissionList(offset,rows,menu_id);
    }

    public int getPermissionListCount(String menu_id){
        return permissionMapper.getPermissionListCount(menu_id);
    }

    public boolean addMenu(Menu menu){
        if(menuMapper.insert(menu)>0){
            return true;
        }
        return false;
    }

    public boolean updateMenu(Menu menu){
        if(menuMapper.updateByPrimaryKey(menu)>0){
            return true;
        }
        return false;
    }

    public boolean delMenu(String menu_id){
        if(menuMapper.deleteByPrimaryKey(menu_id)>0){
            return true;
        }
        return false;

    }
    public boolean addPermission(Permission permission){
        if(permissionMapper.insert(permission)>0){
            return true;
        }
        return false;
    }

    public boolean updatePermission(Permission permission){
        if(permissionMapper.updateByPrimaryKey(permission)>0){
            return true;
        }
        return false;
    }

    public boolean delPermission(String perm_id){
        if(permissionMapper.deleteByPrimaryKey(perm_id)>0){
            return true;
        }
        return false;

    }

}
