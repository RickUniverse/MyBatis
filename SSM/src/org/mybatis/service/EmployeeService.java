package org.mybatis.service;

import org.mybatis.dao.EmployeeMapper;
import org.mybatis.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lijichen
 * @date 2020/12/7 - 21:53
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getEmployees() {
        return employeeMapper.getEmployees();
    }
}
