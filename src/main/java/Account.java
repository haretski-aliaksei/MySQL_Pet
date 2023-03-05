import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Account {
    private int accountId;
    private int userId;
    private int balance;
    private String currency;

    public Account(int inputUserId, int inputBalance, String inputCurrency) {
        this.userId = inputUserId;
        this.balance = inputBalance;
        this.currency = inputCurrency;

        if (inputBalance <= 0 || inputBalance > 2_000_000_000) {
            System.out.println("Balance should be more than \"0\" and less or equal \"2_000_000_000\"");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String sqlCommandCreateUser = "INSERT INTO accounts(userId, balance, currency) VALUES (?, ?, ?)";
            String sqlCommandCheckCurrency = "SELECT userid, currency FROM accounts WHERE userid=?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmtCreateUser = conn.prepareStatement(sqlCommandCreateUser);
                 PreparedStatement pstmtCheckCurrency = conn.prepareStatement(sqlCommandCheckCurrency)) {

                pstmtCheckCurrency.setInt(1, inputUserId);
                ResultSet resultSet = pstmtCheckCurrency.executeQuery();

                while (resultSet.next()) {
                    String existedCurrency = resultSet.getString("currency");

                    if (existedCurrency.equals(inputCurrency)) {
                        System.out.println("This user already has an account with this currency");
                        return;
                    }
                }

                pstmtCreateUser.setInt(1, inputUserId);
                pstmtCreateUser.setInt(2, inputBalance);
                pstmtCreateUser.setString(3, inputCurrency);
                pstmtCreateUser.executeUpdate();

                System.out.println("Account is created");
            }
        } catch (Exception ex) {
            System.out.println("Connection to database failed...");
            System.out.println(ex);
        }
    }
}
