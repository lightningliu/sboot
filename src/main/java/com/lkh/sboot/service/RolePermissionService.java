package com.lkh.sboot.service;

import com.lkh.sboot.entity.RolePermission;
import com.lkh.sboot.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    public boolean addRolePermission(RolePermission rolePermission){
        if(rolePermissionMapper.insert(rolePermission)>0){
            return true;
        }
        return false;
    }
    public boolean delRolePermissionByRoleId(String role_id){
        if(rolePermissionMapper.delRolePermissionByRoleId(role_id)>0){
            return true;
        }
        return false;
    }
}
