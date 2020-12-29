package com.mybatis.entities;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author lijichen
 * @date 2020/12/5 - 18:55
 */
@Alias("employee")//起别名
public class Employee implements Serializable {
    private static final long serialVersionUID = -89184289009156100L;
    private Integer id;
    private String lastName;
    private int gender;
    private String email;
    private EmpEnum empState = EmpEnum.LOGOUT;

    private Department department;

    public EmpEnum getEmpState() {
        return empState;
    }

    public void setEmpState(EmpEnum empState) {
        this.empState = empState;
    }

    public Employee(Integer id, String lastName, int gender, String email) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public Employee() {
    }

    public Employee(Integer id, String lastName, int gender, String email, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", empState=" + empState +
                ", department=" + department +
                '}';
    }
}
