package com.mybatis.dao;

import com.mybatis.entities.Department;

public interface DepartmentMapper {

    //分步查询
    Department getDepartmentByIdPulsStep(Integer id);

    //获取Department中的Employee集合
    Department getDepartmentByIdPuls(Integer id);

    Department getDepartmentById(Integer id);

}
