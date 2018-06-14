public class ConnectionDemo01 {
    public static final String str = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        try {
            Class.forName(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
