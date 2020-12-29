package com.mybatis.entities;

import java.io.Serializable;
import java.util.List;

/**
 * @author lijichen
 * @date 2020/12/6 - 18:16
 */
public class Department implements Serializable {
    private static final long serialVersionUID = -4928004404209470800L;
    private Integer id;
    private String deptName;
    private List<Employee> employees;

    public Department() {
    }

    public Department(Integer id, String deptName) {
        this.id = id;
        this.deptName = deptName;
    }

    public Department(Integer id, String deptName, List<Employee> employees) {
        this.id = id;
        this.deptName = deptName;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
