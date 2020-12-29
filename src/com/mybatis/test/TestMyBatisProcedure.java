package com.mybatis.test;

import com.mybatis.dao.EmployeeMapper;
import com.mybatis.entities.Employee;
import com.mybatis.entities.Page;
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
import java.util.List;
import java.util.UUID;

/**
 * @author lijichen
 * @date 2020/12/5 - 18:57
 */
public class TestMyBatisProcedure {

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
    public void tesProcedure() {
        Page page = new Page();
        page.setStart(1);
        page.setEnd(5);
        /*  创建存储过程：
            create or replace procedure
                   hello_test(
                      p_start in int, p_end in int, p_count out int, p_emps out sys_refcursor
                   ) as
            begin
                   select count(*) into p_count from EMPLOYEE_TABLE;

                   open p_emps for
                        select * from (select rownum rn, e.* from EMPLOYEE_TABLE e where rownum <= p_end)
                               where rn>=p_start;
            end hello_test;
        * */
        employeeMapper.getPageByProcedure(page);
        System.out.println(page.getEmps());
        System.out.println(page.getEmps().size());

    }

}
