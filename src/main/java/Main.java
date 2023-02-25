import java.sql.Connection;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String sqlCommand = "DELETE FROM users WHERE name='Bob'";

            try (Connection conn = DatabaseConnection.getConnection()) {


                Statement statement = conn.createStatement();
                int value = statement.executeUpdate(sqlCommand);


                System.out.println(value);

            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
}
