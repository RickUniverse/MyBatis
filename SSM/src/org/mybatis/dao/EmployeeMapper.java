package org.mybatis.dao;

import org.mybatis.entities.Employee;

import java.util.List;

public interface EmployeeMapper {

    Employee getEmployeeById(Integer id);

    List<Employee> getEmployees();
}
