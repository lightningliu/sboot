package com.lkh.sboot.entity;

public class Dept {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dept.dept_id
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    private String dept_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dept.dept_name
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    private String dept_name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dept.parent_dept_id
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    private String parent_dept_id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_dept.dept_order
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    private Integer dept_order;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dept.dept_id
     *
     * @return the value of sys_dept.dept_id
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    public String getDept_id() {
        return dept_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dept.dept_id
     *
     * @param dept_id the value for sys_dept.dept_id
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dept.dept_name
     *
     * @return the value of sys_dept.dept_name
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    public String getDept_name() {
        return dept_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dept.dept_name
     *
     * @param dept_name the value for sys_dept.dept_name
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dept.parent_dept_id
     *
     * @return the value of sys_dept.parent_dept_id
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    public String getParent_dept_id() {
        return parent_dept_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dept.parent_dept_id
     *
     * @param parent_dept_id the value for sys_dept.parent_dept_id
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    public void setParent_dept_id(String parent_dept_id) {
        this.parent_dept_id = parent_dept_id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_dept.dept_order
     *
     * @return the value of sys_dept.dept_order
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    public Integer getDept_order() {
        return dept_order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_dept.dept_order
     *
     * @param dept_order the value for sys_dept.dept_order
     *
     * @mbg.generated Wed Jun 06 10:04:02 CST 2018
     */
    public void setDept_order(Integer dept_order) {
        this.dept_order = dept_order;
    }
}