package cn.tedu;



import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
    private static DruidDataSource ds;
    static {
        //创建表示连接池的对象
        ds = new DruidDataSource();
        //设置链接数据库的信息
        ds.setUrl("jdbc:mysql://localhost:3306/hr?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false");
        ds.setUsername("root");
        ds.setPassword("root");//写自己的密码
        //设置初始连接数量
        ds.setInitialSize(3);
        //设置最大连接数量
        ds.setMaxActive(5);
    }
    public static Connection getConn() throws SQLException {
        //从连接池中获取链接  异常抛出
        Connection conn = ds.getConnection();
        System.out.println("连接对象:"+conn);

        return conn;
    }
}
