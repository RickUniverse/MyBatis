package com.mybatis.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.dao.DepartmentMapper;
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
import java.util.List;

/**
 * @author lijichen
 * @date 2020/12/5 - 18:57
 */
public class TestMyBatisPlusPageHelper {

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

        //必须在开始分页后 紧接着调用mapper的查询方法，顺序不能乱
        PageHelper.startPage(5,1);//开启分页
        List<Employee> employees = employeeMapperPlus.getEmployees();
        /*
        * list : employees: 分页后的集合
        * navigatePage：5 ：连续显示的页数，比如当前页是4 ，连续显示五页， 即显示的页数为 2 3 4 5 6
        * */
        PageInfo pageInfo = new PageInfo(employees,5);
        employees.forEach(System.out::println);
        System.out.println("当前页"+pageInfo.getPageNum());
        System.out.println("下一页"+pageInfo.getNextPage());
        System.out.println("总数据量"+pageInfo.getTotal());
        System.out.println("总页数"+pageInfo.getPages());
        System.out.println("是否是第一页"+pageInfo.isIsFirstPage());
        System.out.println("是否最后一页"+pageInfo.isIsLastPage());

        int[] navigatepageNums = pageInfo.getNavigatepageNums();
        for (int navigatepageNum : navigatepageNums) {
            System.out.println("第：" + navigatepageNum +"页");
        }

    }


}
