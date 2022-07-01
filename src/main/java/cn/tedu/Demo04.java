package cn.tedu;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Demo04 {
    public static void main(String[] args) throws SQLException {
        //创建表示连接池的对象
        DruidDataSource ds = new DruidDataSource();
        //设置链接数据库的信息
        ds.setUrl("jdbc:mysql://localhost:3306/hr?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false");
        ds.setUsername("root");
        ds.setPassword("root");//写自己的密码
        //设置初始连接数量
        ds.setInitialSize(3);
        //设置最大连接数量
        ds.setMaxActive(5);
        //从连接池中获取链接  异常抛出
        Connection conn = ds.getConnection();
        System.out.println("连接对象:"+conn);

    }
}
