import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseSetup.initialize();

        MemberDAO memberDAO = new MemberDAO();
        ItemDAO itemDAO = new ItemDAO();
        LoanDAO loanDAO = new LoanDAO();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            printMenu();
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine().trim());
            System.out.println();

            switch (choice) {
                case 1 -> itemDAO.browseItems();
                case 2 -> {
                    System.out.print("Enter keyword to search: ");
                    itemDAO.searchItems(scanner.nextLine());
                }
                case 3 -> {
                    System.out.print("Enter Library Card Number: ");
                    Member m = memberDAO.findMember(scanner.nextLine());
                    if (m == null) { System.out.println("  Member not found. Please register first."); break; }
                    itemDAO.browseItems();
                    System.out.print("Enter Item ID to borrow: ");
                    loanDAO.borrowItem(m, scanner.nextLine());
                }
                case 4 -> {
                    System.out.print("Enter Library Card Number: ");
                    Member m = memberDAO.findMember(scanner.nextLine());
                    if (m == null) { System.out.println("  Member not found."); break; }
                    System.out.print("Enter Item ID to return: ");
                    loanDAO.returnItem(m, scanner.nextLine());
                }
                case 5 -> {
                    System.out.print("Enter Full Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Contact Number: ");
                    String contact = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    memberDAO.registerMember(name, contact, address);
                }
                case 0 -> System.out.println("  Goodbye! Thank you for using Library System.");
                default -> System.out.println("  Invalid choice. Please try again.");
            }

            if (choice != 0) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        } while (choice != 0);
        scanner.close();
    }

    static void printMenu() {
        System.out.println("=====================================");
        System.out.println("     LIBRARY MANAGEMENT SYSTEM      ");
        System.out.println("=====================================");
        System.out.println("  [1] Browse Catalog");
        System.out.println("  [2] Search Catalog");
        System.out.println("  [3] Borrow Item");
        System.out.println("  [4] Return Item");
        System.out.println("  [5] Register Member");
        System.out.println("  [0] Exit");
        System.out.println("=====================================");
    }
}