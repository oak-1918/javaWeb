package com.hzj.test;

import com.hzj.utils.JdbcUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author hzj
 * @create 2022-02-24 17:03
 */
public class JdbcUtilsTest {

    @Test
    public void testConnection1() throws SQLException {
        //获取实现类对象
        Driver driver = new com.mysql.jdbc.Driver();
        //jdbc:mysql:协议   localhost:ip地址  3306:默认端口号 book_hzj:数据库
        String url = "jdbc:mysql://localhost:3306/book_hzj?useUnicode=true&characterEncoding=utf8";
        Properties info = new Properties();
        System.out.println("封装用户名和密码");
        info.setProperty("user", "root");
        info.setProperty("password", "abc123");
        System.out.println("创建链接");
        Connection conn = (Connection) driver.connect(url,info);
        System.out.println("结束");
        System.out.println(conn);
    }
    //方式二：使用DriverManager替换Driver
    @Test
    public void testConnection2() throws Exception {
        //1.获取实现类对象 使用反射
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        //2.提供要连接的数据库
        //jdbc:mysql:协议   localhost:ip地址  3306:默认端口号 book_hzj:数据库
        String url = "jdbc:mysql://localhost:3306/book_hzj?useUnicode=true&characterEncoding=utf8";
        //3.提供要连接的用户名和密码
        Properties info = new Properties();
        System.out.println("封装用户名和密码");
        info.setProperty("user", "root");
        info.setProperty("password", "abc123");
        System.out.println("创建链接");
        //4.获取连接
        Connection conn = (Connection) driver.connect(url,info);
        System.out.println("结束");
        System.out.println(conn);
    }
    //方式三：使用DriverManager替换Driver
    @Test
    public void testConnection3() throws Exception {
        //1.获取实现类对象 使用反射
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        //2.提供另外三个连接的基本信息
        //jdbc:mysql:协议   localhost:ip地址  3306:默认端口号 book_hzj:数据库
        String url = "jdbc:mysql://localhost:3306/book_hzj?useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String password = "abc123";
        //4.获取连接
        //注册驱动
        DriverManager.registerDriver(driver);
        System.out.println(DriverManager.getConnection(url, user, password));

    }

    //方式四：优化使用DriverManager替换Driver
    @Test
    public void testConnection4() throws Exception {




        //1.提供三个连接的基本信息
        //jdbc:mysql:协议   localhost:ip地址  3306:默认端口号 book_hzj:数据库
        String url = "jdbc:mysql://localhost:3306/book_hzj?useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String password = "abc123";

        //加载驱动
         Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        //4.获取连接
        System.out.println(DriverManager.getConnection(url, user, password));

    }

    //方式四：将数据库需要的4个基本信息声明在配置文件中，通过读取配置文件的方式来获取连接
    @Test
    public void testConnection5() throws Exception {

        //1.读取配置文件中的4个基本信息
        //jdbc:mysql:协议   localhost:ip地址  3306:默认端口号 book_hzj:数据库
        InputStream is = JdbcUtilsTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        System.out.println("is"+is);
        Properties properties = new Properties();
        properties.load(is);
        String user = properties.getProperty("username");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClassName");
        Class.forName(driverClass);
        java.sql.Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

    }

    @Test
    public void testJdbcUtils() throws IOException {
//        for (int i = 0; i < 100; i++) {
//
//            java.sql.Connection connection = JdbcUtils.getConnection();
//            System.out.println(i +"i" + connection);
//            JdbcUtils.close(connection);
//        }
    }
}
