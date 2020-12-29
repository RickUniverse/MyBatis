package com.mybatis.dao;

import com.mybatis.entities.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSQL {

    List<Employee> queryConditionInnerParam(Employee employee);

    long batchInsert(@Param("emps") List<Employee> epms);

    List<Employee> queryConditionWhereEmpsByEmp(Employee employee);

    List<Employee> queryConditionChooseEmpsByEmp(Employee employee);

    List<Employee> queryConditionForEach(@Param("ids") List<Integer> ids);

    boolean updateConditionSetAndTrim(Employee employee);
}
