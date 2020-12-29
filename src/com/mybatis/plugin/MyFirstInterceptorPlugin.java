package com.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.annotation.Target;
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
public class MyFirstInterceptorPlugin implements Interceptor {

    /**
     * 拦截目标对象的目标方法的 执行
     * @param invocation
     * @return 调用目标方法后返回
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstInterceptorPlugin[intercept method](Invocation.getMethod()):" + invocation.getMethod());

        //获取拦截的当前实例
        Object target = invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(target);
        //获取属性值
        System.out.println("Value :　" + metaObject.getValue("parameterHandler.parameterObject"));

        //设置属性值，使用分页插件了，注释掉
        /*metaObject.setValue("parameterHandler.parameterObject",12);*/

        Object proceed = invocation.proceed();//掉用目标方法

        return proceed;
    }

    /**
     * 包装目标对象的 ：包装，为目标对象创建一个代理对象
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {

        System.out.println("NOW Target:" + target);

        //使用当前interceptor包装目标对象
        Object wrap = Plugin.wrap(target,this);

        return wrap;
    }

    //注册插件时传进来的 properties属性
    @Override
    public void setProperties(Properties properties) {
        System.out.println("properties : " + properties);
    }
}
