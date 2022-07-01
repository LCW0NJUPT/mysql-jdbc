package cn.tedu;

import java.sql.*;
import java.util.Scanner;

public class Demo07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
//        username = "'"+username+"'";
//        password = "'"+password+"'";
        //获取链接
        try (Connection conn = DBUtils.getConn()){
//            Statement s = conn.createStatement();
//            String sql = "select count(*) from user where username="
//                    +username+" and password="+password;
//            System.out.println(sql);
//            ResultSet rs = s.executeQuery(sql);
            String sql = "select count(*) from user where username=? and password=?";
            //PreparedStatement预编译的SQL执行对象, 在创建对象时将SQL语句中的逻辑判断部分进行了编译
            //可以理解为将SQL语句中的逻辑部分锁死,只留下?等待将用户输入的内容替换进来
            //当把?内容替换时 会以值的方式进行处理 不会再对原有SQL语句的逻辑产生影响,从而避免了SQL注入
            PreparedStatement ps = conn.prepareStatement(sql);
            //替换SQL语句中的?    1和2 代表的是?的位置
            //如果替换?时用的是setString方法 字符串的两边会自动添加引号
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            //让游标往下移动
            rs.next();
            int count = rs.getInt(1);
            if (count>0){
                System.out.println("登录成功!");
            }else{
                System.out.println("用户名或密码错误!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
