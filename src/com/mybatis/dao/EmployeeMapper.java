package com.mybatis.dao;

import com.mybatis.entities.Employee;
import com.mybatis.entities.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    /*
    * 调用存储过程分页数据
    * */
    void getPageByProcedure(Page page);

    //返回key是id值是Employee对象，需要指定key对应的列即@MapKey("id")，xml的resultVal为Employee全类名
    @MapKey("id")
    Map<Integer, Employee> getEmployeeMapByLike(String lastName);

    //返回一个map，key就是数据表的列，值就是数据表的值
    Map<String,Object> getMapById(Integer id);

    List<Employee> getListByLike(@Param("lastName") String lastName);

    Employee getEmployeeByMap(Map<String,Object> map);

    Employee getEmployeeById(Integer id);

    Integer updateEmployee(Employee employee);

    Long addEmployee(Employee employee);

    boolean deleteEmployee(Integer id);

    //实用命名参数
    Employee getEmployeeByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);
}
