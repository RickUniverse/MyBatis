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
public class TestMyBatis {

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


    //测试集合
    @Test
    public void tesCollection() {
        System.out.println(employeeMapper.getListByLike("%张%"));

        System.out.println(employeeMapper.getMapById(1));

        System.out.println(employeeMapper.getEmployeeMapByLike("%张%"));
    }


    @Test
    public void testParam() {
        System.out.println(employeeMapper.getEmployeeByIdAndLastName(3, "张武"));
        Map<String, Object> map = new HashMap<>();
        map.put("id",3);
        map.put("lastName","张武");
        map.put("tableName","tb_employee");
        System.out.println(employeeMapper.getEmployeeByMap(map));
    }
    @Test
    public void testCUD() {
        sqlSession = sqlSessionFactory.openSession(false);//默认false不自动提交
//        sqlSession = sqlSessionFactory.openSession(true);//true自动提交
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = new Employee(1, "张武", 1, null);

        Long aLong = mapper.addEmployee(employee);
        System.out.println(employee.getId());

//        Integer integer = mapper.updateEmployee(employee);
//        boolean b = mapper.deleteEmployee(2);
        sqlSession.commit();

    }
    @Test
    public void testInterfaceMapperAnnotation() {
        EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
        System.out.println(mapper.getClass());
        Employee employee = mapper.getEmployeeById(1);
        System.out.println(employee);
    }
    @Test
    public void testInterfaceMapper() {
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        System.out.println(mapper.getClass());
        Employee employee = mapper.getEmployeeById(100);
        System.out.println(employee);
    }








    /**
     * 1，根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.能直接执行已经映射后的sql语句
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3，使用完成需要关闭
        try {
            Employee employee = sqlSession.selectOne("com.mybatis.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }
}
