import java.sql.Connection;
import java.sql.PreparedStatement;

public class Account {
    private int accountId;
    private int userId;
    private int balance;
    private String currency;

    public Account(int userId, int balance, String currency) {
        this.userId = userId;
        this.balance = balance;
        this.currency = currency;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String sqlCommand = "INSERT INTO accounts(userId, balance, currency) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sqlCommand)) {

                pstmt.setInt(1, userId);
                pstmt.setInt(2, balance);
                pstmt.setString(3, currency);
                pstmt.executeUpdate();

                System.out.println("Account is created");
            }
        } catch (Exception ex) {
            System.out.println("Connection to database failed...");
            System.out.println(ex);
        }
    }








    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
