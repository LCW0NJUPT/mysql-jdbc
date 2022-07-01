package cn.tedu;

import jdk.nashorn.internal.codegen.DumpBytecode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        try (Connection conn = DBUtils.getConn()){
            String sql = "select password from user where username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            //替换?
            ps.setString(1,username);
            //执行查询
            ResultSet rs = ps.executeQuery();
            //判断是否查询到数据
            if (rs.next()){//代表用户名存在
                //得到数据库中查询到的正确的密码
                String pw = rs.getString(1);
                if (password.equals(pw)){
                    System.out.println("恭喜你登录成功!");
                }else{
                    System.out.println("密码错误!");
                }
            }else{
                System.out.println("用户名不存在!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
