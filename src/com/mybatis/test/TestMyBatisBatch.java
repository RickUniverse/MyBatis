package com.mybatis.test;

import com.mybatis.dao.EmployeeMapper;
import com.mybatis.dao.EmployeeMapperAnnotation;
import com.mybatis.entities.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
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
import java.util.UUID;

/**
 * @author lijichen
 * @date 2020/12/5 - 18:57
 */
public class TestMyBatisBatch {

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


    //批量添加
    @Test
    public void tesBatch() {
        SqlSession sqlSession2 = sqlSessionFactory.openSession(ExecutorType.BATCH);//批量添加使用
        EmployeeMapper employeeMapper = sqlSession2.getMapper(EmployeeMapper.class);
        long millis = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            employeeMapper.addEmployee(new Employee(0, UUID.randomUUID().toString().substring(1,4),1,"email"));
        }
        System.out.println( System.currentTimeMillis() - millis);

    }

}
