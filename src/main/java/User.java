import java.sql.Connection;
import java.sql.PreparedStatement;

public class User {
    private String name;
    private String address;

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

                System.out.println("User is added");
            }
        } catch (Exception ex) {
            System.out.println("Connection to database failed...");
            System.out.println(ex);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}