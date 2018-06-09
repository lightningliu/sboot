package com.lkh.sboot.mapper;

import com.lkh.sboot.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    int insert(RoleMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_menu
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    int insertSelective(RoleMenu record);

    int delRoleMenuByRoleId(@Param("role_id") String role_id);
}