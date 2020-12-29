package com.mybatis.typehandler;

import com.mybatis.entities.EmpEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lijichen
 * @date 2020/12/9 - 19:50
 */
public class MyEnumTypeHandler implements TypeHandler<EmpEnum> {

    //设置参数
    @Override
    public void setParameter(PreparedStatement ps, int i, EmpEnum parameter, JdbcType jdbcType) throws SQLException {
        System.out.println("向数据库中保存了状态码：" + parameter.getCode());
        ps.setString(i,parameter.getCode().toString());
    }

    @Override
    public EmpEnum getResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        EmpEnum empEnumByCode = EmpEnum.getEmpEnumByCode(code);
        return empEnumByCode;
    }

    @Override
    public EmpEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        return EmpEnum.getEmpEnumByCode(rs.getInt(columnIndex));
    }

    /*
    * CallableStatement:存储过程调用时
    * */
    @Override
    public EmpEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return EmpEnum.getEmpEnumByCode(cs.getInt(columnIndex));
    }
}
