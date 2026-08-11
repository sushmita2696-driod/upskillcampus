import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BankingInformationSystem {
    static Scanner scanner = new Scanner(System.in);
    static List<BankAccount> accounts = new ArrayList<>();
    static int nextAccountNumber = 1001;

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("       WELCOME TO BANKING INFORMATION SYSTEM");
        System.out.println("==============================================");
        while (true) {
            displayMenu();
            int choice = getIntegerInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    displayAllAccounts();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    transferMoney();
                    break;
                case 7:
                    showTransactionHistory();
                    break;
                case 8:
                    searchAccount();
                    break;
                case 9:
                    deleteAccount();
                    break;
                case 10:
                    System.out.println("\nThank you for using Banking Information System!");
                    System.out.println("Have a nice day!");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice!");
                    System.out.println("Please enter a number between 1 and 10.");
            }
        }
    }
    
    // DISPLAY MENU


    static void displayMenu() {

        System.out.println("\n----------------------------------------------");
        System.out.println("              BANKING MENU");
        System.out.println("----------------------------------------------");
        System.out.println("1. Create Account");
        System.out.println("2. Display All Accounts");
        System.out.println("3. Deposit Money");
        System.out.println("4. Withdraw Money");
        System.out.println("5. Check Balance");
        System.out.println("6. Transfer Money");
        System.out.println("7. Transaction History");
        System.out.println("8. Search Account");
        System.out.println("9. Delete Account");
        System.out.println("10. Exit");
        System.out.println("----------------------------------------------");
    }


    // CREATE ACCOUNT
  
    static void createAccount() {

        System.out.println("\n==============================================");
        System.out.println("              CREATE ACCOUNT");
        System.out.println("==============================================");

        scanner.nextLine();

        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        while (name.trim().isEmpty()) {
            System.out.print("Name cannot be empty. Enter name again: ");
            name = scanner.nextLine();
        }

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        while (!phone.matches("\\d{10}")) {
            System.out.print("Enter a valid 10-digit phone number: ");
            phone = scanner.nextLine();
        }

        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.println("\nSelect Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");

        int typeChoice = getIntegerInput("Enter choice: ");

        String accountType;

        if (typeChoice == 1) {
            accountType = "Savings";
        } else if (typeChoice == 2) {
            accountType = "Current";
        } else {
            System.out.println("Invalid account type.");
            return;
        }

        double initialDeposit = getDoubleInput("Enter initial deposit: ");

        if (initialDeposit < 0) {
            System.out.println("Initial deposit cannot be negative.");
            return;
        }

        int accountNumber = nextAccountNumber++;

        BankAccount account = new BankAccount(
                accountNumber,
                name,
                phone,
                email,
                address,
                accountType,
                initialDeposit
        );

        accounts.add(account);

        System.out.println("\n==============================================");
        System.out.println("       ACCOUNT CREATED SUCCESSFULLY");
        System.out.println("==============================================");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + name);
        System.out.println("Account Type   : " + accountType);
        System.out.printf("Initial Balance: ₹%.2f%n", initialDeposit);
        System.out.println("==============================================");
    }


    // DISPLAY ALL ACCOUNTS

    static void displayAllAccounts() {

        System.out.println("\n==============================================");
        System.out.println("             ALL BANK ACCOUNTS");
        System.out.println("==============================================");

        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        for (BankAccount account : accounts) {
            account.displayBasicDetails();
        }
    }


    // DEPOSIT MONEY
  
    static void depositMoney() {

        System.out.println("\n==============================================");
        System.out.println("              DEPOSIT MONEY");
        System.out.println("==============================================");

        int accountNumber =
                getIntegerInput("Enter account number: ");

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        double amount =
                getDoubleInput("Enter amount to deposit: ");

        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }

        account.deposit(amount);

        System.out.println("\nDeposit successful!");
        System.out.printf("Deposited Amount : ₹%.2f%n", amount);
        System.out.printf("New Balance      : ₹%.2f%n", account.getBalance());
    }


    // WITHDRAW MONEY

    static void withdrawMoney() {

        System.out.println("\n==============================================");
        System.out.println("             WITHDRAW MONEY");
        System.out.println("==============================================");

        int accountNumber =
                getIntegerInput("Enter account number: ");

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        double amount =
                getDoubleInput("Enter amount to withdraw: ");

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }

        if (account.withdraw(amount)) {

            System.out.println("\nWithdrawal successful!");
            System.out.printf("Withdrawn Amount : ₹%.2f%n", amount);
            System.out.printf("Remaining Balance: ₹%.2f%n",
                    account.getBalance());

        } else {

            System.out.println("\nInsufficient balance!");
            System.out.printf("Available Balance: ₹%.2f%n",
                    account.getBalance());
        }
    }


    // CHECK BALANCE
    
    static void checkBalance() {

        System.out.println("\n==============================================");
        System.out.println("              BALANCE ENQUIRY");
        System.out.println("==============================================");

        int accountNumber =
                getIntegerInput("Enter account number: ");

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("\nAccount Holder : " + account.getName());
        System.out.println("Account Number : " + account.getAccountNumber());
        System.out.printf("Current Balance: ₹%.2f%n",
                account.getBalance());
    }
=
    // TRANSFER MONEY

    static void transferMoney() {

        System.out.println("\n==============================================");
        System.out.println("              MONEY TRANSFER");
        System.out.println("==============================================");

        int senderNumber =
                getIntegerInput("Enter sender account number: ");

        BankAccount sender = findAccount(senderNumber);

        if (sender == null) {
            System.out.println("Sender account not found.");
            return;
        }

        int receiverNumber =
                getIntegerInput("Enter receiver account number: ");

        BankAccount receiver = findAccount(receiverNumber);

        if (receiver == null) {
            System.out.println("Receiver account not found.");
            return;
        }

        if (senderNumber == receiverNumber) {
            System.out.println("Sender and receiver accounts cannot be the same.");
            return;
        }

        double amount =
                getDoubleInput("Enter transfer amount: ");

        if (amount <= 0) {
            System.out.println("Transfer amount must be greater than zero.");
            return;
        }

        if (sender.withdrawForTransfer(amount)) {

            receiver.depositForTransfer(amount);

            System.out.println("\n==============================================");
            System.out.println("          TRANSFER SUCCESSFUL");
            System.out.println("==============================================");

            System.out.println("From Account : " + senderNumber);
            System.out.println("To Account   : " + receiverNumber);
            System.out.printf("Amount       : ₹%.2f%n", amount);

            System.out.printf("Sender Balance: ₹%.2f%n",
                    sender.getBalance());

            System.out.printf("Receiver Balance: ₹%.2f%n",
                    receiver.getBalance());

        } else {

            System.out.println("\nTransfer failed!");
            System.out.println("Insufficient balance.");
        }
    }

   
    // TRANSACTION HISTORY

    static void showTransactionHistory() {

        System.out.println("\n==============================================");
        System.out.println("            TRANSACTION HISTORY");
        System.out.println("==============================================");

        int accountNumber =
                getIntegerInput("Enter account number: ");

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("\nAccount Holder: " + account.getName());
        System.out.println("Account Number: " + account.getAccountNumber());

        System.out.println("\nTransaction History:");
        System.out.println("----------------------------------------------");

        if (account.getTransactions().isEmpty()) {
            System.out.println("No transactions available.");
        } else {

            for (String transaction : account.getTransactions()) {
                System.out.println(transaction);
            }
        }

        System.out.println("----------------------------------------------");
        System.out.printf("Current Balance: ₹%.2f%n",
                account.getBalance());
    }

    // SEARCH ACCOUNT
   
    static void searchAccount() {

        System.out.println("\n==============================================");
        System.out.println("               SEARCH ACCOUNT");
        System.out.println("==============================================");

        scanner.nextLine();

        System.out.print("Enter account holder name: ");
        String searchName = scanner.nextLine();

        boolean found = false;

        for (BankAccount account : accounts) {

            if (account.getName()
                    .toLowerCase()
                    .contains(searchName.toLowerCase())) {

                account.displayBasicDetails();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No account found with this name.");
        }
    }

   
    // DELETE ACCOUNT
   
    static void deleteAccount() {

        System.out.println("\n==============================================");
        System.out.println("              DELETE ACCOUNT");
        System.out.println("==============================================");

        int accountNumber =
                getIntegerInput("Enter account number: ");

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("\nAccount found:");
        account.displayBasicDetails();

        scanner.nextLine();

        System.out.print("\nAre you sure you want to delete this account? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {

            if (account.getBalance() > 0) {

                System.out.println(
                        "Account cannot be deleted while balance is greater than zero."
                );

                System.out.printf(
                        "Current Balance: ₹%.2f%n",
                        account.getBalance()
                );

                return;
            }

            accounts.remove(account);

            System.out.println("Account deleted successfully.");

        } else {

            System.out.println("Account deletion cancelled.");
        }
    }

    
    // FIND ACCOUNT
   
    static BankAccount findAccount(int accountNumber) {

        for (BankAccount account : accounts) {

            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        return null;
    }

   
    // INTEGER INPUT VALIDATION

    static int getIntegerInput(String message) {

        while (true) {

            System.out.print(message);

            if (scanner.hasNextInt()) {

                int value = scanner.nextInt();
                return value;

            } else {

                System.out.println(
                        "Invalid input! Please enter a number."
                );

                scanner.next();
            }
        }
    }

    // DOUBLE INPUT VALIDATION
    
    static double getDoubleInput(String message) {

        while (true) {

            System.out.print(message);

            if (scanner.hasNextDouble()) {

                double value = scanner.nextDouble();
                return value;

            } else {

                System.out.println(
                        "Invalid amount! Please enter a valid number."
                );

                scanner.next();
            }
        }
    }
}

// BANK ACCOUNT CLASS

class BankAccount {

    private int accountNumber;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String accountType;
    private double balance;

    private List<String> transactions;

    // Constructor
    public BankAccount(
            int accountNumber,
            String name,
            String phone,
            String email,
            String address,
            String accountType,
            double initialDeposit) {

        this.accountNumber = accountNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.accountType = accountType;
        this.balance = initialDeposit;

        transactions = new ArrayList<>();

        if (initialDeposit > 0) {

            transactions.add(
                    "Initial Deposit: ₹"
                            + String.format("%.2f", initialDeposit)
            );
        }
    }

    
    // DEPOSIT

    public void deposit(double amount) {

        balance += amount;

        transactions.add(
                "Deposit: +₹"
                        + String.format("%.2f", amount)
                        + " | Balance: ₹"
                        + String.format("%.2f", balance)
        );
    }

   
    // WITHDRAW
  
    public boolean withdraw(double amount) {

        if (amount > balance) {
            return false;
        }

        balance -= amount;

        transactions.add(
                "Transfer Received: +₹"
                        + String.format("%.2f", amount)
                        + " | Balance: ₹"
                        + String.format("%.2f", balance)
        );
    }


    // DISPLAY ACCOUNT DETAILS
    
    public void displayBasicDetails() {

        System.out.println("\n----------------------------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + name);
        System.out.println("Phone Number   : " + phone);
        System.out.println("Email          : " + email);
        System.out.println("Address        : " + address);
        System.out.println("Account Type   : " + accountType);
        System.out.printf("Balance        : ₹%.2f%n", balance);
        System.out.println("----------------------------------------------");
    }


    // GETTERS
 
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactions() {
        return transactions;
    }
