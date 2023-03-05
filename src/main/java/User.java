import java.sql.Connection;
import java.sql.PreparedStatement;

public class User {
    private int userId;
    private String name;
    private String address;

    public User(String name) {
        this.name = name;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String sqlCommand = "INSERT INTO users(name) VALUES (?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sqlCommand)) {

                pstmt.setString(1, name);
                pstmt.executeUpdate();

                System.out.println("User is created");
            }
        } catch (Exception ex) {
            System.out.println("Connection to database failed...");
            System.out.println(ex);
        }
    }

    public User(String name, String address) {
        this.name = name;
        this.address = address;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String sqlCommand = "INSERT INTO users(name, address) VALUES (?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sqlCommand)) {

                pstmt.setString(1, name);
                pstmt.setString(2, address);
                pstmt.executeUpdate();

                System.out.println("User is created");
            }
        } catch (Exception ex) {
            System.out.println("Connection to database failed...");
            System.out.println(ex);
        }
    }
}
