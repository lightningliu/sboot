package com.lkh.sboot.service;

import com.lkh.sboot.entity.Role;
import com.lkh.sboot.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleService {
    @Resource
    private RoleMapper roleMapper;


    public List<Map<String,Object>> getList(){
        return roleMapper.getList();
    }

    public boolean addRole(Role role){
        if(roleMapper.insert(role)>0){
            return true;
        }
        return false;
    }

    public boolean updateRole(Role role){
        if(roleMapper.updateByPrimaryKey(role)>0){
            return true;
        }
        return false;
    }

    public boolean delRole(String role_id){
        if(roleMapper.deleteByPrimaryKey(role_id)>0){
            return true;
        }
        return false;

    }
}
