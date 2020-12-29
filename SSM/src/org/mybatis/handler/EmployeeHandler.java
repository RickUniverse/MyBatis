package org.mybatis.handler;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.entities.Employee;
import org.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lijichen
 * @date 2020/12/7 - 21:56
 */
@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeService employeeService;

    //注入批量删除的sqlSession
    @Resource(name = "sqlSessionBatch")
    private SqlSession sqlSession;

    @ResponseBody
    @RequestMapping("/emps")
    public String getEmps() {
        List<Employee> employees = employeeService.getEmployees();
        System.out.println(sqlSession.getClass());
        return employees.toString();
    }
}
