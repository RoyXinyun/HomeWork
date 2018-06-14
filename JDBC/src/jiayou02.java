import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jiayou02 {
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/xinyun?serverTimezone=UTC";
    public static final String password = "192223";
    public static final String user = "root";
    public static void main(String[] args) throws Exception{
       Connection con = null;
       Statement sta = null;
       Class.forName(driver);

       con = DriverManager.getConnection(url,user,password);
       sta = con.createStatement();
       String sql1 = "insert into mytab(name,password,age,sex,birthday)" + "values('张维利','zong990919',45,'男','1974-07-04')";

       String sql2 = "delete from mytab where id = 3";
       sta.execute(sql2);

       sta.close();
       con.close();
    }
}
