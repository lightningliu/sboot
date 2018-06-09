package com.lkh.sboot.service;

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
public class PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private MenuMapper menuMapper;


    public List<Map<String,Object>> getMenuAndPerssionList_tree(String role_id,String parent_menu_id){
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list= menuMapper.getListByParentMenuIdAndRole(parent_menu_id,role_id);
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map=new HashMap<String,Object>();
            List<Map<String,Object>> list1=menuMapper.getListByParentMenuIdAndRole(list.get(i).get("menu_id")+"",role_id);
            //存在叶子节点，选中状态看子节点
            if(list1.size()>0){//说明有子节点
                List<Map<String,Object>> list3=this.getMenuAndPerssionList_tree(role_id,list.get(i).get("menu_id")+"");
                int checksize=0;
                for (int m = 0; m < list3.size(); m++) {
                    if ("1".equals(((Map)list3.get(m).get("attributes")).get("checked")+"")){//选中状态
                        checksize++;
                    }
                }
                if (checksize==list3.size()){
                    map.put("checked",true);
                }else{
                    map.put("checked",false);
                }
                map.put("children",list3);
            }else{
                //查询该菜单下的功能权限
                List<Map<String,Object>> list2=permissionMapper.getPermissionListByMenuIdAndRole(list.get(i).get("menu_id")+"",role_id);
                int checksize=0;
                if (list2!=null && list2.size()>0){
                    List<Map<String,Object>> templist=new ArrayList<Map<String,Object>>();
                    for (int j = 0; j < list2.size() ; j++) {
                        Map<String,Object> temmap=new HashMap<String,Object>();
                        temmap.put("id",list2.get(j).get("perm_id")+"");
                        temmap.put("text",list2.get(j).get("perm_name")+"");
                        Map<String,Object> attributes1=new HashMap<>();
                        attributes1.put("type","2");
                        attributes1.put("url",list2.get(j).get("perm_flag")+"");
                        temmap.put("attributes",attributes1);
                        if ("1".equals(list2.get(j).get("checked")+"")){
                            temmap.put("checked",true);
                            checksize++;
                        }else{
                            temmap.put("checked",false);
                        }
                        templist.add(temmap);

                    }
                    map.put("children",templist);

                    if (checksize==list2.size()){
                        map.put("checked",true);
                    }else{
                        map.put("checked",false);
                    }

                }else{
                    if ("1".equals(list.get(i).get("checked")+"")){
                        map.put("checked",true);
                    }else{
                        map.put("checked",false);
                    }

                }
            }

            map.put("id",list.get(i).get("menu_id")+"");
            map.put("text",list.get(i).get("menu_name")+"");
            Map<String,Object> attributes1=new HashMap<>();
            attributes1.put("type","1");
            attributes1.put("url",list.get(i).get("menu_url")+"");
            map.put("attributes",attributes1);

            result.add(map);
        }
        return result;
    }


    public List<Map<String,Object>> getListByRole(String role_id){
        return permissionMapper.getPermissionListByRole(role_id);
    }
}
