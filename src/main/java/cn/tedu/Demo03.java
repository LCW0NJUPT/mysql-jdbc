package cn.tedu;

import java.sql.*;

public class Demo03 {
    public static void main(String[] args) throws SQLException {
        //获取链接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false","root","Whdx@yznyyzx");
        //创建执行SQL语句的对象
        Statement s = conn.createStatement();
        //执行插入数据的SQL语句
        int n = s.executeUpdate("insert into departments(department_name) values('研发部')");
        System.out.println("插入了"+n+"条");
        //执行修改数据的SQL语句
        n = s.executeUpdate("update departments set department_name='销售部' where department_name='研发部'");
        System.out.println("修改了"+n+"条");
        //执行删除数据的SQL语句
        n = s.executeUpdate("delete from departments where department_name in ('研发部', '销售部')");
        System.out.println("删除了"+n+"条");
        //执行查询数据的SQL语句
        ResultSet rs = s.executeQuery("select department_id,department_name from departments");
        //遍历结果集对象
        while(rs.next()){//向下移动游标，向服务器请求这行数据，从服务器获取数据，并返回数据是否存在
            //通过查询到的字段名获取数据
            int id = rs.getInt("department_id");
            String name = rs.getString("department_name");
            System.out.println(id+", "+name);
        }
        //关闭资源
        conn.close();

    }
}
