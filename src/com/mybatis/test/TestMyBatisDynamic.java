package com.mybatis.test;

import com.mybatis.dao.DepartmentMapper;
import com.mybatis.dao.EmployeeMapperDynamicSQL;
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
import java.util.Arrays;
import java.util.List;

/**
 * @author lijichen
 * @date 2020/12/5 - 18:57
 */
public class TestMyBatisDynamic {

    private SqlSessionFactory sqlSessionFactory = null;
    private SqlSession sqlSession = null;
    private EmployeeMapperDynamicSQL employeeMapperDynamicSQL = null;
    private DepartmentMapper departmentMapper = null;


    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //开启
        sqlSession = sqlSessionFactory.openSession();

        employeeMapperDynamicSQL = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
        departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
    }
    @After
    public void after() {
        sqlSession.close();
    }


    //使用DynamicSQL
    @Test
    public void testIf() {
//        List<Employee> employeeList = employeeMapperDynamicSQL.queryConditionWhereEmpsByEmp(new Employee(null, "%张%", 1, "邮件"));
//        List<Employee> employeeList = employeeMapperDynamicSQL.queryConditionChooseEmpsByEmp(new Employee(null, "%张%", 1, "邮件"));
//        boolean flag = employeeMapperDynamicSQL.updateConditionSetAndTrim(new Employee(1, "%张%", 1, "邮件"));
//        List<Employee> employees = employeeMapperDynamicSQL.queryConditionForEach(Arrays.asList(1, 2, 3, 4));
        long insert = employeeMapperDynamicSQL.batchInsert(Arrays.asList(
                new Employee(null, "溜溜1", 1, "email", new Department(1, null))
//                new Employee(null, "溜溜22", 1, "email", new Department(1, null)),
//                new Employee(null, "溜溜3", 1, "email", new Department(1, null)),
//                new Employee(null, "溜溜4", 1, "email", new Department(1, null)),
//                new Employee(null, "溜溜5", 1, "email", new Department(1, null)),
//                new Employee(null, "溜溜6", 1, "email", new Department(1, null)),
//                new Employee(null, "溜溜7", 1, "email", new Department(1, null)),
//                new Employee(null, "溜溜8", 1, "email", new Department(1, null)),
//                new Employee(null, "溜溜9", 1, "email", new Department(1, null)),
//                new Employee(null, "溜溜10", 1, "email", new Department(1, null))
                ));
        System.out.println(insert);//如果添加oracle，因为使用的是begin end所以返回-1
//        List<Employee> employees = employeeMapperDynamicSQL.queryConditionInnerParam(
//                new Employee(null, "溜", 1, "邮件"));
//        employees.forEach(System.out::println);
        sqlSession.commit();
    }

}
