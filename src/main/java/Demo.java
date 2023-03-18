import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        System.out.println("What operation do you want to perform?\n" +
                "- Enter \"1\" to create an account;\n" +
                "- Enter \"2\" to add an account to the user;\n" +
                "- Enter \"3\" to replenish the user's account;\n" +
                "- Enter \"4\" to withdraw funds from the user's account.\n" +
                "Input number of operation:");

        Scanner scanner = new Scanner(System.in);
        String chosenOperation = scanner.nextLine();

        switch (chosenOperation) {
            case "1":
                System.out.println("Input the name of new user:");
                String userName = scanner.nextLine();
                User user = new User(userName);
                break;
            case "2":
                System.out.println("Input User ID:");
                int userId = scanner.nextInt();
                System.out.println("Input initial balance:");
                int initialBalance = scanner.nextInt();
                System.out.println("Input type of Currency:");
                String currency = scanner.nextLine();
                Account account = new Account(userId, initialBalance, currency);
                break;
            case "3":
                System.out.println("Input account ID:");
                int accountIdReplenishment = scanner.nextInt();
                System.out.println("How much do you want to top up your account? Enter amount:");
                int replenishmentAmount = scanner.nextInt();
                Transaction transactionReplenishment = new Transaction();
                transactionReplenishment.replenishmentBalance(accountIdReplenishment, replenishmentAmount);
                break;
            case "4":
                System.out.println("Input account ID:");
                int accountIdWithdrawal = scanner.nextInt();
                System.out.println("How much do you want to withdraw from your account? Enter amount:");
                int withdrawalAmount = scanner.nextInt();
                Transaction transactionWithdrawal = new Transaction();
                transactionWithdrawal.withdrawalFromBalance(accountIdWithdrawal, withdrawalAmount);
                break;
            default:
                System.out.println("Possible input values:\n" +
                        "- 1;\n" +
                        "- 2;\n" +
                        "- 3;\n" +
                        "- 4.\n" +
                        "You entered an invalid value. The program has ended.");
        }
        scanner.close();
    }
}
