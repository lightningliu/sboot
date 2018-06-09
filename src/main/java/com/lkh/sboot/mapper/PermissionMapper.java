package com.lkh.sboot.mapper;

import com.lkh.sboot.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    int deleteByPrimaryKey(String perm_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    int insert(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    int insertSelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    Permission selectByPrimaryKey(String perm_id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    int updateByPrimaryKey(Permission record);
    public List<Map<String,Object>> getPermissionList(@Param("offset") int offset, @Param("rows") int  rows, @Param("menu_id") String menu_id);
    public int getPermissionListCount(@Param("menu_id") String menu_id);


    public List<Map<String,Object>> getPermissionListByMenuIdAndRole(@Param("menu_id") String menu_id,@Param("role_id") String role_id);
    public List<Map<String,Object>> getPermissionListByRole(@Param("role_id") String role_id);
}