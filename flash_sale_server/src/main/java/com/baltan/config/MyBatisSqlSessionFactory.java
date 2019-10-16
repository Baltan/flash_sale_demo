package com.baltan.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-10-15 14:47
 */
public class MyBatisSqlSessionFactory {
    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 加载 mybatis-config.xml，创建 SqlSessionFactory 实例
     * 每个数据库环境应该就只有一个 SqlSessionFactory 对象实例
     * 所以使用单例模式只创建一个 SqlSessionFactory 实例
     *
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            synchronized (MyBatisSqlSessionFactory.class) {
                if (sqlSessionFactory == null) {
                    InputStream inputStream;
                    try {
                        inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "development");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession openSqlSession() {
        return getSqlSessionFactory().openSession();
    }
}