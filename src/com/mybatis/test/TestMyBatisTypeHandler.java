package com.mybatis.test;

import com.mybatis.dao.EmployeeMapper;
import com.mybatis.entities.EmpEnum;
import com.mybatis.entities.Employee;
import com.mybatis.entities.Page;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lijichen
 * @date 2020/12/5 - 18:57
 */
public class TestMyBatisTypeHandler {

    private SqlSessionFactory sqlSessionFactory = null;
    private SqlSession sqlSession = null;
    private EmployeeMapper employeeMapper = null;


    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //开启
        sqlSession = sqlSessionFactory.openSession();

        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
    }
    @After
    public void after() {
        sqlSession.close();
    }


    //添加

    /**
     * 指定typeHandler的方式
     *  添加时：#{empState,typeHandler=com.mybatis.typehandler.MyEnumTypeHandler}
     *  查询使用resultMap时：<result column="emp_state" property="empState" typeHandler="com.mybatis.typehandler.MyEnumTypeHandler"/>
     *  如果添加时指定了TypeHandler，则查询时也需要使用相同的typeHandler
     */
    @Test
    public void tesHandler() {
        Employee employee = new Employee(null,"asdf",1,"sdf");
        employee.setEmpState(EmpEnum.getEmpEnumByCode(100));
        employeeMapper.addEmployee(employee);
        System.out.println(employee.getId());
        Employee employeeById = employeeMapper.getEmployeeById(employee.getId());
        System.out.println(employeeById);

        sqlSession.commit();
    }

}
