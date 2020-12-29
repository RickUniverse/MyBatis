package com.mybatis.dao;

import com.mybatis.entities.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapperPlus {

    //按照部门查询员工集合
    List<Employee> getEmployeeByDeptId(Integer id);

    List<Employee> getEmployees();

    //分步查询
    Employee getEmployeeAndDepartmentByIdStep(Integer id);

    //级联属性赋值
    Employee getEmployeeAndDepartmentById(Integer id);

    //自定义封装规则
    Employee getEmployeeById(Integer id);
}
