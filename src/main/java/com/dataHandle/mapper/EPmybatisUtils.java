package com.dataHandle.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class EPmybatisUtils {
    public static SqlSessionFactory getFactory(){
        String resource="sqlite.xml";

        //加载mybatis 的配置文件（它也加载关联的映射文件）
        InputStream is=EPmybatisUtils.class.getClassLoader().getResourceAsStream(resource);

        //构建sqlSession 的工厂
        SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = sfb.build(is);
        return factory;
    }
}
