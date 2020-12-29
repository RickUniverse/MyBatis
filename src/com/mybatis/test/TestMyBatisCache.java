package com.mybatis.test;

import com.mybatis.dao.EmployeeMapper;
import com.mybatis.dao.EmployeeMapperAnnotation;
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
public class TestMyBatisCache {

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


    //测试二级缓存
    @Test
    public void tesSecondLevelCache() {
        Employee employee = employeeMapper.getEmployeeById(1);
        System.out.println(employee);
        sqlSession.close();

        sqlSession = sqlSessionFactory.openSession();
        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee2 = employeeMapper.getEmployeeById(1);
        System.out.println(employee);
        System.out.println(employee == employee2);
    }

    //测试一级缓存
    @Test
    public void tesFirstLevelCache() {
        Employee employee = employeeMapper.getEmployeeById(1);

        sqlSession.clearCache();//一级缓存失效

        Employee employee2 = employeeMapper.getEmployeeById(1);

        System.out.println(employee == employee2);
    }


}
