package cn.tedu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Demo06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        System.out.println("请输入昵称");
        String nick = sc.nextLine();
        //获取链接
        try (Connection conn = DBUtils.getConn()){
            Statement s = conn.createStatement();
            s.execute("create table if not exists user(" +
                    "id int primary key auto_increment," +
                    "username varchar(30)," +
                    "password varchar(30)," +
                    "nick varchar(30));");

            //执行插入数据的SQL语句  insert into user values(null,'tom','123456','汤姆');
            s.executeUpdate("insert into user(username, password, nick) values('"
                    +username+"','"+password+"','"+nick+"')");
            System.out.println("注册完成!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
