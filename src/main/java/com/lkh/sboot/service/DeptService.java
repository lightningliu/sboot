package com.lkh.sboot.service;

import com.lkh.sboot.entity.Dept;
import com.lkh.sboot.mapper.DeptMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DeptService {
    @Resource
    private DeptMapper deptMapper;


    public List<Map<String,Object>> getDeptList_gridtree(String parent_dept_id){
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list= deptMapper.getListByParentDeptId(parent_dept_id);
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map=list.get(i);
            List<Map<String,Object>> list1=deptMapper.getListByParentDeptId(map.get("dept_id")+"");
            if(list1.size()>0){//说明有子节点
                map.put("children",this.getDeptList_gridtree(map.get("dept_id")+""));
            }
            result.add(map);
        }
        return result;
    }

    public List<Map<String,Object>> getDeptList_combotree(String parent_dept_id){
        List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> list= deptMapper.getListByParentDeptId(parent_dept_id);
        for (int i = 0; i < list.size(); i++) {
            Map<String,Object> map=new HashMap<String,Object>();
            List<Map<String,Object>> list1=deptMapper.getListByParentDeptId(list.get(i).get("dept_id")+"");
            if(list1.size()>0){//说明有子节点
                map.put("children",this.getDeptList_combotree(list.get(i).get("dept_id")+""));
            }
            map.put("id",list.get(i).get("dept_id")+"");
            map.put("text",list.get(i).get("dept_name")+"");
            result.add(map);
        }
        return result;
    }


    public boolean addDept(Dept dept){
        if(deptMapper.insert(dept)>0){
            return true;
        }
        return false;
    }

    public boolean updateDept(Dept dept){
        if(deptMapper.updateByPrimaryKey(dept)>0){
            return true;
        }
        return false;
    }

    public boolean delDept(String dept_id){
        if(deptMapper.deleteByPrimaryKey(dept_id)>0){
            return true;
        }
        return false;

    }

}
