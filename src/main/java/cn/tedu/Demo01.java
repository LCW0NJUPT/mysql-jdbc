package cn.tedu;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //加载mysql驱动类的类文件，自动执行该类中的一个静态代码块
        //静态块中执行DriverManager.registerDriver(new Driver());
        //向DriverManager注册驱动实例
        Class.forName("com.mysql.cj.jdbc.Driver");

        //1.建立链接  异常抛出
        Connection conn =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hr?characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false","root","Whdx@yznyyzx");
        System.out.println("链接对象:"+conn);
        //2.创建执行SQL语句的对象
        Statement s = conn.createStatement();
        //3.执行SQL语句  execute=执行
        s.execute("create table jdbct1(id int)");
        //4.关闭资源 断开链接
        conn.close();
        System.out.println("执行完成!");

    }
}
