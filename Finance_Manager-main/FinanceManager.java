import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    private String description;
    private double amount;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}

class Budget {
    private String name;
    private double balance;
    private List<Transaction> transactions;

    public Budget(String name, double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(String description, double amount) {
        Transaction transaction = new Transaction(description, amount);
        transactions.add(transaction);
        balance += amount;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

public class FinanceManager {
    private static List<Budget> budgets = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Finance Manager");
            System.out.println("1. Create Budget");
            System.out.println("2. Record Transaction");
            System.out.println("3. View Budgets");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createBudget();
                    break;
                case 2:
                    recordTransaction();
                    break;
                case 3:
                    viewBudgets();
                    break;
                case 4:
                    System.out.println("Exiting Finance Manager. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createBudget() {
        System.out.print("Enter budget name: ");
        String name = scanner.next();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        Budget budget = new Budget(name, initialBalance);
        budgets.add(budget);
        System.out.println("Budget created successfully.");
    }

    private static void recordTransaction() {
        if (budgets.isEmpty()) {
            System.out.println("No budgets available. Create a budget first.");
            return;
        }

        System.out.println("Select a budget to record the transaction:");
        for (int i = 0; i < budgets.size(); i++) {
            System.out.println((i + 1) + ". " + budgets.get(i).getName());
        }
        System.out.print("Enter the budget number: ");
        int budgetNumber = scanner.nextInt();

        if (budgetNumber < 1 || budgetNumber > budgets.size()) {
            System.out.println("Invalid budget number.");
            return;
        }

        Budget selectedBudget = budgets.get(budgetNumber - 1);
        System.out.print("Enter transaction description: ");
        String description = scanner.next();
        System.out.print("Enter transaction amount: ");
        double amount = scanner.nextDouble();
        selectedBudget.addTransaction(description, amount);
        System.out.println("Transaction recorded successfully.");
    }

    private static void viewBudgets() {
        if (budgets.isEmpty()) {
            System.out.println("No budgets available. Create a budget first.");
            return;
        }

        System.out.println("Budgets:");
        for (int i = 0; i < budgets.size(); i++) {
            Budget budget = budgets.get(i);
            System.out.println("Name: " + budget.getName());
            System.out.println("Balance: $" + budget.getBalance());
            System.out.println("Transactions:");
            for (Transaction transaction : budget.getTransactions()) {
                System.out.println("- " + transaction.getDescription() + ": $" + transaction.getAmount());
            }
            System.out.println();
        }
    }
}