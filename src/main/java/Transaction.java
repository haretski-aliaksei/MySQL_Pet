import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Transaction {
    private int transactionId;
    private int accountId;
    private int amount;

    public void replenishmentBalance(int inputAccountId, int inputAmount) {
        if (inputAmount >= 100_000_000) {
            System.out.println("Maximum transaction size exceeded (MAX = 100.000.000)");
            return;
        }

        int currentUserBalance = getCurrentBalance(inputAccountId);
        if ((currentUserBalance + inputAmount) > 2_000_000_000) {
            System.out.println("Balance can't be more than \"2_000_000_000\"");
            return;
        }

        processTransaction(inputAccountId, inputAmount);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String sqlCommandUpdate = "UPDATE accounts SET balance=? WHERE accountid=?";
            String sqlCommandSelect = "SELECT balance FROM accounts WHERE accountid=?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmtUpdate = conn.prepareStatement(sqlCommandUpdate);
                 PreparedStatement pstmtSelect = conn.prepareStatement(sqlCommandSelect)) {

                pstmtSelect.setInt(1, inputAccountId);
                ResultSet resultSet = pstmtSelect.executeQuery();

                while (resultSet.next()) {
                    int currentBalance = resultSet.getInt("balance");
                    pstmtUpdate.setInt(1, currentBalance + inputAmount);
                    pstmtUpdate.setInt(2, inputAccountId);
                    pstmtUpdate.execute();
                }

                System.out.println("Balance is updated");
            }
        } catch (Exception ex) {
            System.out.println("Connection to database failed...");
            System.out.println(ex);
        }
    }

    public void withdrawalFromBalance(int inputAccountId, int inputAmount) {
        if (inputAmount >= 100_000_000) {
            System.out.println("Maximum transaction size exceeded (MAX = 100.000.000)");
            return;
        }

        int currentUserBalance = getCurrentBalance(inputAccountId);
        if ((currentUserBalance - inputAmount) <= 0) {
            System.out.println("Balance can't be less than \"0\"");
            return;
        }

        processTransaction(inputAccountId, inputAmount);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String sqlCommandUpdate = "UPDATE accounts SET balance=? WHERE accountid=?";
            String sqlCommandSelect = "SELECT balance FROM accounts WHERE accountid=?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmtUpdate = conn.prepareStatement(sqlCommandUpdate);
                 PreparedStatement pstmtSelect = conn.prepareStatement(sqlCommandSelect)) {

                pstmtSelect.setInt(1, inputAccountId);
                ResultSet resultSet = pstmtSelect.executeQuery();

                while (resultSet.next()) {
                    int currentBalance = resultSet.getInt("balance");
                    pstmtUpdate.setInt(1, currentBalance - inputAmount);
                    pstmtUpdate.setInt(2, inputAccountId);
                    pstmtUpdate.execute();
                }

                System.out.println("Balance is updated");
            }
        } catch (Exception ex) {
            System.out.println("Connection to database failed...");
            System.out.println(ex);
        }
    }

    private void processTransaction(int inputAccountId, int inputAmount) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String sqlCommandTransaction = "INSERT INTO transactions(accountId, amount) VALUES (?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sqlCommandTransaction)) {

                pstmt.setInt(1, inputAccountId);
                pstmt.setInt(2, inputAmount);
                pstmt.executeUpdate();
                System.out.println("Transaction is processed");
            }
        } catch (Exception ex) {
            System.out.println("Connection to database failed...");
            System.out.println(ex);
        }
    }

    private int getCurrentBalance(int inputAccountId) {
        int currentBalance = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String sqlCommandSelectBalance = "SELECT balance FROM accounts WHERE accountid=?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sqlCommandSelectBalance)) {

                pstmt.setInt(1, inputAccountId);
                ResultSet resultSet = pstmt.executeQuery();

                while (resultSet.next()) {
                    currentBalance = resultSet.getInt("balance");
                }

            }
        } catch (Exception ex) {
            System.out.println("Connection to database failed...");
            System.out.println(ex);
        }

        return currentBalance;
    }
}
