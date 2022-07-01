package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        System.out.println("请输入昵称");
        String nick = sc.nextLine();
        try (Connection conn = DBUtils.getConn()){
            //先判断用户名是否存在  如果不存在再进行注册
            String sql = "select id from user where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                System.out.println("用户名已存在!");
                return;
            }
            //执行插入数据的SQL
            String insertSql = "insert into user values(null,?,?,?)";
            PreparedStatement insertPs = conn.prepareStatement(insertSql);
            insertPs.setString(1,username);
            insertPs.setString(2,password);
            insertPs.setString(3,nick);
            insertPs.executeUpdate();
            System.out.println("注册成功!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
