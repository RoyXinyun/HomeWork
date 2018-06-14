import java.sql.*;
import java.util.*;
import java.util.Date;

public class ResultSetDemo {
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String user = "root";
    public static String password = "192223";
    public static String url = "jdbc:mysql://localhost:3306/xinyun?serverTimezone=UTC";

    public static void main(String [] args)throws Exception{
        Connection con = null;
        Statement sta = null;
        ResultSet rs = null;

        String sql = "SELECT id,name,age,sex,birthday FROM mytab";
        Class.forName(driver);
        con = DriverManager.getConnection(url,user,password);
        sta = con.createStatement();
        sta.execute("INSERT into mytab(name,password,age,sex,birthday)" + "values('宗莲凤','zong730315',46,'女','1972-03-15')");
        rs = sta.executeQuery(sql);

        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int age = rs.getInt(3);
            String sex = rs.getString(4);
            Date date = rs.getDate(5);
            System.out.println(id + "  " + name + "  " + age + "  " + sex + "  " + date);
            sta = con.createStatement();
        }


        rs.close();
        sta.close();
        con.close();
    }
}
