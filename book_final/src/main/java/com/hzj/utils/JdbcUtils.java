package com.hzj.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.net.CookieHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author hzj
 * @create 2022-02-24 15:21
 */
public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();
    static{
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库连接池中的链接
     * @return 如果返回null,说明获取连接失败， 有值就是获取连接成功
     */
    public static Connection getConnection(){
        Connection conn = conns.get();
        if(conn == null){
            try {
                conn = dataSource.getConnection();//从数据库连接池中获取连接
                conns.set(conn); // 保存到 ThreadLocal 对象中，供后面的 jdbc 操作使用
                conn.setAutoCommit(false); // 设置为手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return conn;

    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose(){
        Connection conn = conns.get();
        if(conn != null){//// 如果不等于 null，说明 之前使用过连接，操作过数据库
            try {
                conn.commit();
            } catch (SQLException e) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        conns.remove();// 一定要执行 remove 操作，否则就会出错。（因为 Tomcat 服务器底层使用了线程池技术）

    }
    public static void rollbackAndClose(){
        Connection conn = conns.get();
        if(conn != null){
            try {
                conn.rollback();
            } catch (SQLException e) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        conns.remove();// 一定要执行 remove 操作，否则就会出错。（因为 Tomcat 服务器底层使用了线程池技术）
    }

//    public static void close(Connection conn){
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
}
