package com.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

/**
 * mybatis插件
 * @author lijichen
 * @date 2020/12/8 - 20:00
 */
//声明插件
@Intercepts({
        /*
        * 签名要拦截的四大对象
        *   type：四大对象的类型
        *   method：四大对象的那个方法
        *   args：四大对象方法的类型
        * */
        @Signature(type = StatementHandler.class,method = "parameterize",args = Statement.class)
})
public class MySecondInterceptorPlugin implements Interceptor {

    /**
     * 拦截目标对象的目标方法的 执行
     * @param invocation
     * @return 调用目标方法后返回
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MySecondInterceptorPlugin[intercept method](Invocation.getMethod()):" + invocation.getMethod());
        return invocation.proceed();
    }

    /**
     * 包装目标对象的 ：包装，为目标对象创建一个代理对象
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("NOW Target:" + target);
        return Plugin.wrap(target,this);
    }

    //注册插件时传进来的 properties属性
    @Override
    public void setProperties(Properties properties) {
        System.out.println("properties : " + properties);
    }
}
