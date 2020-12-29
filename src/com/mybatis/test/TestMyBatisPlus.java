package com.mybatis.test;

import com.mybatis.dao.DepartmentMapper;
import com.mybatis.dao.EmployeeMapper;
import com.mybatis.dao.EmployeeMapperAnnotation;
import com.mybatis.dao.EmployeeMapperPlus;
import com.mybatis.entities.Department;
import com.mybatis.entities.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lijichen
 * @date 2020/12/5 - 18:57
 */
public class TestMyBatisPlus {

    private SqlSessionFactory sqlSessionFactory = null;
    private SqlSession sqlSession = null;
    private EmployeeMapperPlus employeeMapperPlus = null;
    private DepartmentMapper departmentMapper = null;


    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //开启
        sqlSession = sqlSessionFactory.openSession();

        employeeMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
        departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
    }
    @After
    public void after() {
        sqlSession.close();
    }


    //使用鉴别器
    @Test
    public void testDiscriminator() {
        Employee employee = employeeMapperPlus.getEmployeeById(1);
        System.out.println(employee);
        System.out.println(employee.getDepartment());
    }
    //测试级联属性分步查
    @Test
    public void testDeptAndEmpsStep() {
        Department department = departmentMapper.getDepartmentByIdPulsStep(1);
        System.out.println(department.getDeptName());
        System.out.println(department);//这样会直接将所有Employee查询出来
//        System.out.println(department.getEmployees());
    }

    //测试级联属性
    @Test
    public void testDeptAndEmps() {
        Department department = departmentMapper.getDepartmentByIdPuls(1);
        System.out.println(department);
        System.out.println(department.getEmployees());

    }
    //测试级联属性
    @Test
    public void testStepEmpAndDep() {
        Employee e = employeeMapperPlus.getEmployeeAndDepartmentByIdStep(1);
        System.out.println(e.getId());
    }
    //测试级联属性
    @Test
    public void testEmpAndDep() {
        System.out.println(employeeMapperPlus.getEmployeeAndDepartmentById(1));
    }
    //测试集合
    @Test
    public void testPlus() {
        System.out.println(employeeMapperPlus.getEmployeeById(1));
    }


}
