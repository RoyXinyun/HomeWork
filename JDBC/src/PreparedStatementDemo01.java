
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class PreparedStatementDemo01 {
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/xinyun?serverTimezone=UTC";
    public static final String passwordd = "192223";
    public static final String user = "root";
    public static void main(String[] args)throws Exception{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.util.Date temp = null;
        String name = "";
        String password = "";
        String sex = "";
        String birthday = "";
        int age = 0;
        Scanner input = new Scanner(System.in);

        System.out.println("************************数据库管理系统**********************");
        System.out.println("1.输入");
        System.out.println("2.查找");
        System.out.println("3.删除");
        System.out.println("4.查看");
        System.out.println("5.退出");
        System.out.println("请选择您要的选项：");
        int n = input.nextInt();
        input.nextLine();
        //数据库启动
        Class.forName(driver);
        con = DriverManager.getConnection(url,user,passwordd);

        boolean flag = true;
        while(flag){
        switch(n){
            case 1:{
                System.out.println("请输入名字：");
                name = input.nextLine();
                System.out.println("请输入密码：");
                password = input.nextLine();
                System.out.println("请输入年龄; ");
                age = input.nextInt();
                input.nextLine();
                System.out.println("请输入性别： ");
                sex = input.nextLine();
                System.out.println("请输入生日： （yyyy-mm-dd）");
                birthday = input.nextLine();
                //将DATE类型转化为SQL DATE类型
                temp = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
                java.sql.Date bir = new java.sql.Date(temp.getTime());
                String sql = "INSERT into mytab(name,password,age,sex,birthday)" + "values(?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1,name);
                ps.setString(2,password);
                ps.setInt(3,age);
                ps.setString(4,sex);
                ps.setDate(5,bir);
                ps.executeUpdate();
                break;
            }
            case 2:{
                System.out.println("请输入您要查找的关键字;");
                String keyword = input.nextLine();
                String sql = "Select * from mytab where name like ? or password like ? or sex like ?";
                ps = con.prepareStatement(sql);
                ps.setString(1,"%" + keyword + "%");
                ps.setString(2,"%" + keyword + "%");
                ps.setString(3,"%" + keyword + "%");
                rs = ps.executeQuery();
                sort(rs);
                break;
            }
            case 3:{
                System.out.println("全部删除(0) or 部分删除(1)？");
                int key = input.nextInt();
                if(key == 0){
                    String sql = "delete frmo mytab";
                    ps = con.prepareStatement(sql);
                    ps.execute();
                    System.out.println("已全部删除！");
                }
                else{
                    System.out.println("请选择删除的ID(空格隔开)");
                    String str = input.next();
                    String delete[] = str.split(" ");
                    for(int i = 0;i < delete.length;i++){
                        String sql = "delete from mytab " + "where id = ?";
                        ps = con.prepareStatement(sql);
                        ps.setString(1,delete[i]);
                        ps.execute();
                    }
                    System.out.println("已删除！");
                }
                break;
            }
            case 4:{
                String sql = "Select id,name,password,age,sex,birthday from mytab";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                sort(rs);
                break;
            }
            case 5:{
                flag = false;
                break;
            }
        }
        if(n != 5){
            System.out.println("请选择您要的选项：");
            n = input.nextInt();
            input.nextLine();
        }
    }
    rs.close();
        ps.close();
        con.close();
    }

    public static void sort(ResultSet rs)throws Exception{
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String pass = rs.getString(3);
            int age = rs.getInt(4);
            String sex = rs.getString(5);
            Date date = rs.getDate(6);
            System.out.println(id + "  " + name + "  " + pass + "  " + age + "  " + sex + "  " + date);
        }
    }
}
