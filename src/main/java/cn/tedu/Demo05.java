package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo05 {
    public static void main(String[] args) {
        //从工具类中获取链接对象
        try (Connection conn = DBUtils.getConn()){
            //创建执行SQL语句的对象
            Statement s = conn.createStatement();
            //执行查询
            ResultSet rs = s.executeQuery("select employee_id,first_name,salary from employees");
            //遍历结果集对象
            while(rs.next()){
//                String name = rs.getString("first_name");
//                double sal = rs.getDouble("salary");
                //通过查询回来字段的位置获取数据
                String name = rs.getString(2);
                double sal = rs.getDouble(3);
                System.out.println(name+":"+sal);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
