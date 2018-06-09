package com.lkh.sboot.service;

import com.lkh.sboot.entity.RoleMenu;
import com.lkh.sboot.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class RoleMenuService {
    @Resource
    private RoleMenuMapper roleMenuMapper;
    public boolean addRoleMenu(RoleMenu roleMenu){
        if(roleMenuMapper.insert(roleMenu)>0){
            return true;
        }
        return false;
    }

    public boolean  delRoleMenuByRoleId(String role_id){
        if(roleMenuMapper.delRoleMenuByRoleId(role_id)>0){
            return true;
        }
        return false;
    }
}
