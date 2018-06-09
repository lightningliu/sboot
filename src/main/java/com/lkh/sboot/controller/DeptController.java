package com.lkh.sboot.controller;

import com.lkh.sboot.common.AjaxJson;
import com.lkh.sboot.common.Guid;
import com.lkh.sboot.entity.Dept;
import com.lkh.sboot.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/*部门模块*/
@Controller
@RequestMapping("deptController")
public class DeptController {
    @Autowired
    private DeptService deptService;
    /*获取列表-treegrid*/
    @RequestMapping("getDeptList_gridtree")
    @ResponseBody
    @RequiresPermissions("deptController/getDeptList_gridtree")//权限管理;
    public Object getDeptList_gridtree(HttpServletRequest request){
        return deptService.getDeptList_gridtree("-1");
    }
    /*获取列表-combotree*/
    @RequestMapping("getDeptList_combotree")
    @ResponseBody
    @RequiresPermissions("deptController/getDeptList_combotree")//权限管理;
    public Object getDeptList_combotree(HttpServletRequest request){
        return deptService.getDeptList_combotree("-1");
    }
    /*保存操作*/
    @RequestMapping("saveDept")
    @ResponseBody
    @RequiresPermissions("deptController/saveDept")//权限管理;
    public AjaxJson saveDept(Dept dept){
        AjaxJson ajaxJson=new AjaxJson();
        if(dept.getParent_dept_id()==null || "".equals(dept.getParent_dept_id()+"")){
            dept.setParent_dept_id("-1");
        }
        boolean result=false;
        if (dept.getDept_id()==null ||  "".equals(dept.getDept_id())){
            dept.setDept_id(Guid.getGuid());
            result=deptService.addDept(dept);
        }else{
            result=deptService.updateDept(dept);
        }
        if(result){
            ajaxJson.setMsg("操作成功");
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }
    /*删除操作*/
    @RequestMapping("delDept")
    @ResponseBody
    @RequiresPermissions("deptController/delDept")//权限管理;
    public AjaxJson delDept(String dept_id){
        AjaxJson ajaxJson=new AjaxJson();
        if(deptService.delDept(dept_id)){
            ajaxJson.setMsg("操作成功");
            ajaxJson.setSuccess(true);
        }
        return ajaxJson;
    }


}
