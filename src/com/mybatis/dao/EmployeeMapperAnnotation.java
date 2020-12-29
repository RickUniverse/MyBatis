package com.mybatis.dao;

import com.mybatis.entities.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnnotation {
    //简单不重要的sql逻辑放在这里
    @Select("select * from tb_employee where id=#{id}")
    Employee getEmployeeById(Integer id);
}
